$(function() {
	// 获取productid的值
	var productId = getQueryString('productId');
	// 通过productid获取商品信息的URL
	var infoUrl = '/o2o/shopadmin/getproductbyid?productId=' + productId;
	// 商铺列表URL
	var categoryUrl = '/o2o/shopadmin/getproductcategorylist';
	//添加商品URL
	var productPostUrl = '/o2o/shopadmin/modifyproduct';
	
	var isEdit = false;
	if (productId) {
		getInfo(productId);
		isEdit = true;
	} else {
		getCategory();
		productPostUrl = '/o2o/shopadmin/addproduct';
	}
	// 获取需要编辑的商品的商品信息，并赋值给表单
	function getInfo(id) {
		$.getJSON(
			infoUrl,
			function(data) {
				if (data.success) {
					// 从返回的JSON当中获取product对象的信息，并赋值给表单
					var product = data.product;
					$('#product-name').val(product.productName);
					$('#product-desc').val(product.productDesc);
					$('#priority').val(product.priority);
					$('#normal-price').val(product.normalPrice);
					$('#promotion-price').val(
							product.promotionPrice);
					// 获取原本的商品类别以及该店铺的所有商品类列表
					var optionHtml = "";
					var optionArr = data.productCategoryList;
					var optionSelected = product.productCategory.productCategoryId;
					// 生成前端的HTML商品类别列表，并默认选择编辑前的商品类别
					optionArr
							.map(function(item, index) {
								var isSelect = optionSelected === item.productCategoryId ? 'select'
										: '';
								optionHtml += '<option data-value="'
										+ item.productCategoryId
										+ '"'
										+ isSelect
										+ '>'
										+ item.productCategoryName
										+ '</option>';
							});
					$('#category').html(optionHtml);
				}
			});
	}

	// 为商品添加操作提供该店铺下的所有商品类别列表
	function getCategory() {
		$.getJSON(categoryUrl, function(data) {
			if(data.success){
				var productCategoryList = data.data;
				var optionHtml = '';
				productCategoryList.map(function(item, index) {
					optionHtml += '<option data-value="'
							+ item.productCategoryId + '">'
							+ item.productCategoryName + '</option>';
				});
				$('#category').html(optionHtml);
			}
		});
	}

	$(".detail-img-div").on('change', '.detail-img:last-child', function() {
		if ($('.detail-img').length < 6) {
			$('#detail-img').append('<input type="file" class="detail-img">');
		}
	});
	
	$('#submit').click(
		function(){
			var product = {};
			product.productName = $('#product-name').val();
			product.productDesc = $('#product-desc').val();
			product.priority = $('#priority').val();
			product.normalPrice = $('#normal-price').val();
			product.promotionPrice = $('#promotion-price').val();
			
			product.productCategory = {
				productCategoryId : $('#category').find('option').not(
					function(){
						return !this.selected;
					}).data('value')
			};
			product.productId = productId;
			
			//获取缩略图文件流
			var thumbnail = $('#small-img')[0].files[0];
			//生成表单对象，用于接受参数并传递给后台
			var formData = new FormData();
			formData.append('thumbnail',thumbnail);
			$('.detail-img').map(
				function(index,item){
					if($('.detail-img')[index].files.length > 0){
						formData.append('productImg' + index,
								$('.detail-img')[index].files[0]);
					}
				});
			formData.append('productStr',JSON.stringify(product));
			var verifyCodeActual = $('#j_captcha').val();
			if(!verifyCodeActual){
				$.toast('请输入验证码！');
				return;
			}
			formData.append('verifyCodeActual',verifyCodeActual);
			$.ajax({
				url:productPostUrl,
				type:'POST',
				data:formData,
				contentType:false,
				processData:false,
				cache:false,
				success:function(data){
					if(data.success){
						$.toast('提交成功！');
						$('#captcha_img').click();
					}else{
						$.toast('提交失败！');
						$('#captcha_img').click();
					}
				}
			});
		});
});
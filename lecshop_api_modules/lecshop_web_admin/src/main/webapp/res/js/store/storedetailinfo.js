// 基本路径
var basePath = $("#basePath").val();
// 初始化列表
var initDataTable = function () {
    LSFetch({
        url: basePath + '/querystoredetailinfo.htm',
        data: {
            storeId: $("#storeId").val(),
            status: $("#status").val()
        },
        success: function (data) {
            var storeInfo = data.storeInfo;
            var storeInfoHtml = '';
            storeInfo.bankUserName = storeInfo.bankUserName == null ? "未提交" : storeInfo.bankUserName;
            storeInfo.bankAccount = storeInfo.bankAccount == null ? "未提交" : storeInfo.bankAccount;
            storeInfo.bankName = storeInfo.bankName == null ? "未提交" : storeInfo.bankName;
            storeInfo.bankNumber = storeInfo.bankNumber == null ? "未提交" : storeInfo.bankNumber;
            storeInfo.bankAddress = storeInfo.bankAddress == null ? "未提交" : storeInfo.bankAddress;
            var carPic = storeInfo.carPic == null ? "未提交" : '<a class="btn btn-info btn-xs btn_a" href="' + storeInfo.carPic + '" target="_blank">查看</a>';
            var busLicensePic = storeInfo.busLicensePic == null ? "未提交" : '<a class="btn btn-info btn-xs btn_a" href="' + storeInfo.busLicensePic + '" target="_blank">查看</a>';
            var orgPic = storeInfo.orgPic == null ? "未提交" : '<a class="btn btn-info btn-xs btn_a" href="' + storeInfo.orgPic + '" target="_blank">查看</a>';
            var taxPic = storeInfo.taxPic == null ? "未提交" : '<a class="btn btn-info btn-xs btn_a" href="' + storeInfo.taxPic + '" target="_blank">查看</a>';
            var bankPic = storeInfo.bankPic == null ? "未提交" : '<a class="btn btn-info btn-xs btn_a" href="' + storeInfo.bankPic + '" target="_blank">查看</a>';
            storeInfoHtml += '<tr> <td width="33%">商家编号：' + storeInfo.id + '</td> <td width="33%">店铺名称：' + storeInfo.storeName + '</td> <td>公司名称：' + storeInfo.companyName + '</td> </tr>';
            storeInfoHtml += '<tr> <td>公司邮箱：' + storeInfo.companyEmail + '</td><td>公司电话：' + storeInfo.companyPhone + '</td> <td>公司地址： ' + storeInfo.companyAddress + '</td> </tr>';
            storeInfoHtml += '<tr> <td>公司联系人：' + storeInfo.contactPerson + '</td> <td>联系人电话： ' + storeInfo.contactPhone + '</td> <td>注册号(营业执照号)：' + storeInfo.busLicense + '</td> </tr>';
            storeInfoHtml += ' <tr><td>法定代表人姓名：' + storeInfo.legalPerson + '</td> <td>法定代表人身份证号：' + storeInfo.cardNo + '</td> <td>法人身份证电子版：' + carPic + '</td> </tr>';
            storeInfoHtml += ' <tr><td>营业执照副本电子版：' + busLicensePic + '</td><td>组织机构代码证电子版：' + orgPic + '</td><td>税务登记证电子版：' + taxPic + '</td></tr>';
            storeInfoHtml += ' <tr><td>银行开户名：' + storeInfo.bankUserName + '</td> <td>公司银行账号：' + storeInfo.bankAccount + '</td> <td>开户行支行名称：' + storeInfo.bankName + '</td> </tr>';
            storeInfoHtml += '<tr> <td>开户行支行银行号：' + storeInfo.bankNumber + '</td> <td>开户行支行所在地：' + storeInfo.bankAddress + '</td> <td>银行开户许可证电子版：' + bankPic + '</td> </tr>';
            $("#storeInfo").html(storeInfoHtml);
            var categoryHtml = '';
            for (var i = 0; i < data.threeCategoryList.length; i++) {
                var category = data.threeCategoryList[i];
                categoryHtml += '<li>' + category.name + '</li>';
            }
            $("#category").html(categoryHtml);

            var brandHtml = '';
            for (var j = 0; j < data.storeBrandList.length; j++) {
                var brand = data.storeBrandList[j];
                brandHtml += '<li>' + brand.name + '</li>';
            }
            $("#brand").html(brandHtml);
            var myselfBrandHtml = '';
            for (var k = 0; k < data.mySelfBrandList.length; k++) {
                var myselfBrand = data.mySelfBrandList[k];
                myselfBrandHtml += '<tr><td style="text-align: center;vertical-align: middle;">' + myselfBrand.name + '</td><td style="text-align: center;"><img src="' + myselfBrand.url + '" height="60" alt=""></td>' +
                    '<td style="text-align: center;"><img src="' + myselfBrand.certificatUrl + '" height="60" alt=""></td></tr>';
            }
            $("#myselfBrand").html(myselfBrandHtml);
        }
    });
};

// 初始化列表
initDataTable();

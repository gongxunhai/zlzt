if (user == "" || user == null) {
    alert("请先登录");
    window.location.href = ""+domain+"/pages/fore/users/login.html";
}

document.writeln("<div class=\'banInner banUser wow fadeIn\'>");
document.writeln("	<div class=\'imgFull\'><img src=\'"+domain+"/pages/fore/images/banUser.jpg\' class=\'img-responsive\'></div>");
document.writeln("    <div class=\'banUserCon\'>");
document.writeln("    	<div class=\'userImgBox\' data-toggle=\'modal\' data-target=\'#myHeadImg\'>");
document.writeln("        	<div class=\'userImg\'>");
document.writeln("            	<img id='userImgUpdate' src=\'"+domain+"/statics"+user.headImgUrl+"\' class=\'img-responsive img-circle\'>");
document.writeln("                <div class=\'userImgHov flexBox\'>");
document.writeln("                	<div class=\'userImgHovCon\'>");
document.writeln("                        <i class=\'iconfont icon-ccgl-shujuzidianxiugaijilu-6\'></i>");
document.writeln("                        <p>修改头像</p>");
document.writeln("                    </div>");
document.writeln("                </div>");
document.writeln("            </div>");
document.writeln("        </div>");
document.writeln("        <div class=\'userName\' id=\'userName\'>"+user.username+"</div>");
document.writeln("        <ul class=\'userInfor list-unstyled list-inline\'>");
document.writeln("        	<li><i class=\'iconfont icon-VIP\'></i><strong>会员等级：</strong>vip用户</li>");
document.writeln("        	<li id=\'myOrder\'><i class=\'iconfont icon-dingdan1\'></i><strong>我的订单：</strong>（<a href=\'#\'>3</a>）个</li>");
document.writeln("        	<li id=\'myCollect\'><i class=\'iconfont icon-guanzhu1\'></i><strong>我的收藏：</strong>（<a href=\'#\'>52</a>）个</li>");
document.writeln("        	<li id=\'information\'><i class=\'iconfont icon-tongzhi\'></i><strong>消息通知：</strong>（<a href=\'#\'>7</a>）条</li>");
document.writeln("        	<li id=\'myEntrust\'><i class=\'iconfont icon-weituoweixuanzhong\'></i><strong>我的委托：</strong>（<a href=\'#\'>2</a>）个</li>");
document.writeln("        </ul>");
document.writeln("    </div>");
document.writeln("</div>");
document.writeln("<div class=\'userNavBg\'>");
document.writeln("	<ul class=\'userNav list-unstyled list-inline text-center wow fadeIn\'>");
document.writeln("    	<li><a id=\'mUserCore\' href=\'"+domain+"/pages/fore/users/userCore.html\'>首页</a></li>");
document.writeln("    	<li><a id=\'mCreateProject\' href=\'"+domain+"/pages/fore/users/projectList.html\'>我发布的项目</a></li>");
document.writeln("    	<li><a id=\'mCreateRequire\' href=\'"+domain+"/pages/fore/users/requireList.html\'>我发布的需求</a></li>");
document.writeln("    	<li><a id=\'mOrder\' href=\'"+domain+"/pages/fore/users/order.html\'>我的订单</a></li>");
document.writeln("    	<li><a id=\'mEntrust\' href=\'"+domain+"/pages/fore/users/entrust.html\'>我的委托</a></li>");
document.writeln("    	<li><a id=\'mCollect\' href=\'"+domain+"/pages/fore/users/collection.html\'>我的收藏</a></li>");
document.writeln("    	<li><a id=\'mPonit\' href=\'"+domain+"/pages/fore/users/point.html\'>我已点赞</a></li>");
document.writeln("    	<li><a id=\'mInformation\' href=\'"+domain+"/pages/fore/users/information.html\'>消息通知</a></li>");
document.writeln("    	<li><a id=\'mSetting\' href=\'"+domain+"/pages/fore/users/settingPersonDetail.html\'>账户设置</a></li>");
document.writeln("    </ul>");
document.writeln("</div>");
document.writeln("<div id=\'myHeadImg\' class=\'modal fade\'>");
document.writeln("    <div class=\'loginBox\'>");
document.writeln("        <div class=\'loginTop\'>");
document.writeln("            <h2 class=\'loginTit\'>修改头像</h2>");
document.writeln("            <span class=\'loginClose\' href=\'javascript:;\' data-dismiss=\'modal\'><i class=\'iconfont icon-guanbi\'></i></span>");
document.writeln("            <div class=\'clear\'></div>");
document.writeln("        </div>");
document.writeln("        <div class=\'uploadTip\'>");
document.writeln("            <p><strong>请上传图片：</strong></p>");
document.writeln("        </div>");
document.writeln("        <div class=\'uploadImg\'>");
document.writeln("        	<div class=\'uploadBtn text-center\'>");
document.writeln("               <input type=\'file\' onchange=\'upload()\'  id=\'uploadImg\'>");
document.writeln("                <a href=\'javascript:;\' class=\'loginBtn\'>上传头像</a>");
document.writeln("            </div>");
document.writeln("        </div>");
document.writeln("        <div class=\'goRegister text-center\'>");
document.writeln("            <p>jpg 或 png，大小不超过2M</p>");
document.writeln("        </div>");
document.writeln("        <a href=\'javascript:;\' class=\'uploadExit\' data-dismiss=\'modal\'>取消</a>");
document.writeln("    </div>");
document.writeln("</div>");

function upload() {
    var file = $("#uploadImg")[0].files[0];
    if (file !=null && file !='undefined'){
        var formData = new FormData();
        formData.append("file", file);
        $.ajax({
            type : "post" ,
            url: '/files',
            data : formData ,
            processData : false,
            contentType : false,
            success: function (result) {
                var url = result.url;
                user.headImgUrl = url;
                sessionStorage.setItem("forSession",JSON.stringify(user));
                $.ajax({
                    type : 'put',
                    url : '/zlztUsers',
                    contentType: "application/json; charset=utf-8",
                    async : false,
                    data : JSON.stringify(user),
                    success : function(data) {
                        alert("上传成功");
                        var src = domain + "/statics" + url;
                        $("#userImgUpdate").attr("src", src);
                        $('#myHeadImg').modal('hide');
                    }
                });
            }
        })
    }
}
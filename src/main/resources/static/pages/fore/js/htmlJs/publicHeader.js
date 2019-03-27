document.writeln("<nav class=\'navbar navbar-default bootsnav navbar-fixed-top wow fadeInDown\'>");
document.writeln("    <div class=\'wrap\'>");
document.writeln("        <div class=\'attr-nav navRig\'>");
document.writeln("            <ul>");
document.writeln("            	<li class=\'navTelMob\'><a href=\'tel:010-68376645\'><i class=\'iconfont icon-dianhua\'></i></a></li>");
document.writeln("                <li class=\'navTel\'><a href=\'javascript:;\'><i class=\'iconfont icon-dianhua\'></i><strong>010-68376645</strong></a></li>");
document.writeln("                <li class=\'navSearch\'><a href=\'javascript:;\'><i class=\'iconfont icon-sousuo\'></i>搜索</a></li>");
document.writeln("                <li  id=\'userNav\'><i class=\'iconfont icon-guanjiaowangtubiao01\'></i><a href=\'"+domain+"/pages/fore/users/login.html\'>登录</a><span>or</span><a href=\'"+domain+"/pages/fore/users/register.html\'>注册</a></li>");
document.writeln("            </ul>");
document.writeln("        </div>");
document.writeln("        <div class=\'navbar-header\'>");
document.writeln("            <button type=\'button\' class=\'navbar-toggle\' data-toggle=\'collapse\' data-target=\'#navbar-menu\'>");
document.writeln("                <i class=\'fa fa-bars\'></i>");
document.writeln("            </button>");
document.writeln("            <a class=\'navbar-brand flexBox logo\' href=\'#\'>");
document.writeln("                <span><img src=\'"+domain+"/pages/fore/images/logo.png\' class=\'img-responsive\'></span>");
document.writeln("            </a>");
document.writeln("        </div>            ");
document.writeln("        <div class=\'collapse navbar-collapse\' id=\'navbar-menu\'>");
document.writeln("            <ul class=\'nav navbar-nav navbar-center\' data-in=\'fadeIn\' data-out=\'fadeOut\'>");
document.writeln("                <li id=\'index\'><a href=\'"+domain+"/pages/fore/index.html\'><span>首页</span></a></li>");
document.writeln("                <li class=\'dropdown\' id=\'kjsc\'>");
document.writeln("                      <a href=\'javascript:;\'><span>科技商城</span></a>");
document.writeln("                           <ul class=\'dropdown-menu\'>");
document.writeln("                               <li><a href=\'"+domain+"/pages/fore/kjsc/kjsc.html?id=1\'>科技产品</a></li>");
document.writeln("                               <li><a href=\'"+domain+"/pages/fore/kjsc/kjsc.html?id=2\'>科技成果</a></li>");
document.writeln("                                <li><a href=\'"+domain+"/pages/fore/kjsc/kjsc.html?id=3\'>专利超市</a></li>");
document.writeln("                           </ul>");
document.writeln("                </li>");
document.writeln("                <li id=\'pjRequire\'><a href=\'"+domain+"/pages/fore/projectRequire/pjRequire.html\'><span>项目需求</span></a></li>");
document.writeln("                <li class=\'dropdown\' id=\'kjResource\'>");
document.writeln("                    <a href=\'javascript:;\' class=\'dropdown-toggle\' data-toggle=\'dropdown\' ><span>科技资源</span></a>");
document.writeln("                    <ul class=\'dropdown-menu\'>");
document.writeln("                        <li><a href=\'"+domain+"/pages/fore/lawsRegulations/lawsRegulations.html\'>政策法规</a></li>");
document.writeln("                        <li><a href=\'"+domain+"/pages/fore/zlzt/zlzt.html\'>专利专题</a></li>");
document.writeln("                        <li class=\'dropdown\'>");
document.writeln("                          <a href=\'"+domain+"/pages/fore/ztReport/ztReport.html\' class=\'dropdown-toggle\' data-toggle=\'dropdown\' >专题报告</a>");
document.writeln("                            <ul class=\'dropdown-menu\'>");
document.writeln("                               <li><a href=\'"+domain+"/pages/fore/ztReport/ztReportList.html\'>专利态势分析</a></li>");
document.writeln("                                <li><a href=\'"+domain+"/pages/fore/ztReport/ztReportList.html\'>政策研究</a></li>");
document.writeln("                                <li><a href=\'"+domain+"/pages/fore/ztReport/ztReportList.html\'>情报研究</a></li>");
document.writeln("                            </ul>");
document.writeln("                        </li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
document.writeln("                <li  id=\'zlResult\'><a href=\'"+domain+"/pages/fore/gfArea/zlResult.html\'><span>高分专区</span></a></li>");
document.writeln("                <li  id=\'news\'><a href=\'"+domain+"/pages/fore/news/news.html\'><span>新闻动态</span></a></li>");
document.writeln("                <li class=\'dropdown\'  id=\'kjService\'>");
document.writeln("                     <a href=\'javascript:;\' class=\'dropdown-toggle\' data-toggle=\'dropdown\' ><span>科技服务</span></a>");
document.writeln("                     <ul class=\'dropdown-menu\'>");
document.writeln("                        <li><a href=\'"+domain+"/pages/fore/kjService/kjServiceList.html\'>知识产权服务</a></li>");
document.writeln("                        <li><a href=\'"+domain+"/pages/fore/kjService/kjService.html\'>科技服务</a></li>");
document.writeln("                    </ul>");
document.writeln("                  </li>");
document.writeln("                <li id=\'aboutUs\'><a href=\'"+domain+"/pages/fore/aboutUs.html\'><span>关于我们</span></a></li>");
document.writeln("            </ul>");
document.writeln("        </div>        ");
document.writeln("    </div>");
document.writeln("</nav>");

function checkUser() {
    if (sessionStorage.getItem("forSession") != '' && sessionStorage.getItem("forSession") !=null) {
        user = JSON.parse(sessionStorage.getItem("forSession"));
        $("#userNav").attr("class","navUser dropdown");
        $("#userNav").html('<a href="'+domain+'/pages/fore/users/userCore.html" class="dropdown-toggle" data-toggle="dropdown" >\n' +
            '                    \t<i class="iconfont icon-guanjiaowangtubiao01"><sup></sup></i>\n' +
            '                        <span>'+user.username+'<i class="fa fa-angle-down"></i></span>\n' +
            '                    </a>\n' +
            '                    <ul class="dropdown-menu navUserCon">\n' +
            '                    \t<li>\n' +
            '                        \t<div class="media">\n' +
            '                            \t<div class="media-left media-middle">\n' +
            '                                \t<div class="headerImg"><img src="'+domain+'/statics'+user.headImgUrl+'" class="img-responsive"></div>\n' +
            '                                </div>\n' +
            '                                <div class="media-body">\n' +
            '                                \t<h3 class="navUserName">'+user.username+'</h3>\n' +
            '                                    <p>'+user.email+'</p>\n' +
            '                                    <p>'+user.phone+'</p>\n' +
            '                                </div>\n' +
            '                            </div>\n' +
            '                        </li>\n' +
            '                    \t<li>\n' +
            '                        \t<p><a href="'+domain+'/pages/fore/users/userCore.html">我的个人资料</a></p>\n' +
            '                        \t<p><a href="#">我的订单<span class="corRed">（3）</span></a></p>\n' +
            '                        \t<p><a href="#">消息通知<span class="corRed">（20）</span></a></p>\n' +
            '                        </li>\n' +
            '                    \t<li class="exit">\n' +
            '                        \t<p><a href="javascript:;"><i class="iconfont icon-zhuxiao"></i>退出</a></p>\n' +
            '                        </li>\n' +
            '                    </ul>');
    }
}
var user = '';
checkUser();
 var pathname = window.location.pathname;

console.log(pathname);

var arrA = pathname.split('.');

var arrB = arrA[0].split('/');

var target = arrB[ arrB.length-1 ];

console.log(target);

switch (target) {
    case 'index' : $("#index").addClass("class","active");break;
    case 'kjsc' : $("#kjsc").addClass("class","active");break;
    case 'kjsc_kjproject' : $("#kjsc").addClass("class","active");break;
    case 'kjsc_result' : $("#kjsc").addClass("class","active");break;
    case 'kjsc_zlsuper' : $("#kjsc").addClass("class","active");break;
    case 'pjRequire' : $("#pjRequire").addClass("class","active");break;
    case 'pjRequireDetail' : $("#pjRequire").addClass("class","active");break;
    case 'lawsRegulations' : $("#kjResource").addClass("class","active");break;
    case 'lawsRegulationsDetail' : $("#kjResource").addClass("class","active");break;
    case 'zlzt' : $("#kjResource").addClass("class","active");break;
    case 'zlzt_list' : $("#kjResource").addClass("class","active");break;
    case 'zlzt_list_detail' : $("#kjResource").addClass("class","active");break;
    case 'ztReport' : $("#kjResource").addClass("class","active");break;
    case 'ztReportDetail' : $("#kjResource").addClass("class","active");break;
    case 'ztReportList' : $("#kjResource").addClass("class","active");break;
    case 'zlResult' : $("#zlResult").addClass("class","active");break;
    case 'news' : $("#news").addClass("class","active");break;
    case 'news_detail' : $("#news").addClass("class","active");break;
    case 'kjService' : $("#kjService").addClass("class","active");break;
    case 'kjServiceDetail' : $("#kjService").addClass("class","active");break;
    case 'kjServiceList' : $("#kjService").addClass("class","active");break;
    case 'kjServiceListMore' : $("#kjService").addClass("class","active");break;
    case 'aboutUs' : $("#aboutUs").addClass("class","active");break;
    case 'cy_list' : $("#zlResult").addClass("class","active");break;
    case 'gf_list' : $("#zlResult").addClass("class","active");break;

}



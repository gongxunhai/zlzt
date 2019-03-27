 var pathname = window.location.pathname;

console.log(pathname);

var arrA = pathname.split('.');

var arrB = arrA[0].split('/');

var target = arrB[ arrB.length-1 ];

console.log(target);

switch (target) {
    case 'index' : $("#index").addClass("active");break;
    case 'kjsc' : $("#kjsc").addClass("active");break;
    case 'kjsc_kjproduct' : $("#kjsc").addClass("active");break;
    case 'kjsc_result' : $("#kjsc").addClass("active");break;
    case 'kjsc_zlsuper' : $("#kjsc").addClass("active");break;
    case 'pjRequire' : $("#pjRequire").addClass("active");break;
    case 'pjRequireDetail' : $("#pjRequire").addClass("active");break;
    case 'lawsRegulations' : $("#kjResource").addClass("active");break;
    case 'lawsRegulationsDetail' : $("#kjResource").addClass("active");break;
    case 'zlzt' : $("#kjResource").addClass("active");break;
    case 'zlzt_list' : $("#kjResource").addClass("active");break;
    case 'zlzt_list_detail' : $("#kjResource").addClass("active");break;
    case 'ztReport' : $("#kjResource").addClass("active");break;
    case 'ztReportDetail' : $("#kjResource").addClass("active");break;
    case 'ztReportList' : $("#kjResource").addClass("active");break;
    case 'zlResult' : $("#zlResult").addClass("active");break;
    case 'news' : $("#news").addClass("active");break;
    case 'news_detail' : $("#news").addClass("active");break;
    case 'kjService' : $("#kjService").addClass("active");break;
    case 'kjServiceDetail' : $("#kjService").addClass("active");break;
    case 'kjServiceList' : $("#kjService").addClass("active");break;
    case 'kjServiceListMore' : $("#kjService").addClass("active");break;
    case 'aboutUs' : $("#aboutUs").addClass("active");break;
    case 'cy_list' : $("#zlResult").addClass("active");break;
    case 'gf_list' : $("#zlResult").addClass("active");break;

    case 'userCore' : $("#mUserCore").addClass("active");break;

    case 'requireList' : $("#mCreateRequire").addClass("active");break;
    case 'requireCreate' : $("#mCreateRequire").addClass("active");break;

    case 'projectList' : $("#mCreateProject").addClass("active");break;
    case 'projectCreate' : $("#mCreateProject").addClass("active");break;
}




$(function(){
    initlistdata();
})
function initlistdata() {
    $.ajax({
        type : 'get',
        url : '/zlztClassifyinfos/listdata',
        async : false,
        success : function (data) {
            var result = data;
          //  console.log(JSON.stringify(result));
            var listdata = $("#listdata");
            for (var i in result) {
                var children = result[i].children;
                var a = '';
                for (var j in children){
                    // alert(children[j].name);
                    a += '<li><a href="zlzt_list.html?id='+children[j].id+'&type='+children[j].type+'">'+children[j].name+'</a></li>';
                }
                // console.log(a)
                var li =    '<li class="wow fadeInLeft">\n' +
                    '            \t<div class="resourceBox">\n' +
                    '                \t<div class="resourceLine"></div>\n' +
                    '                \t<div class="resourceTop">\n' +
                    '                        <div class="resourceTit">\n' +
                    '                        \t<h3 ><strong><a href="zlzt_list.html?id='+result[i].id+'&type='+result[i].type+'">'+result[i].name+'</a></strong></h3>\n' +
                    '                        </div>\n' +
                    '                        <div class="clear"></div>\n' +
                    '                    </div>\n' +
                    '                    <div class="resourceBotBox">\n' +
                    '                        <ul class="resourceBot list-unstyled list-inline">\n' + a + '</ul>\n' +
                    '                    </div>\n' +
                    '                    <a href="zlzt_list.html?id='+result[i].id+'&type='+result[i].type+'" class="resourceMore"><i class="iconfont icon-iconjia"></i></a>\n' +
                    '                </div>\n' +
                    '            </li>';
                listdata.append(li);
            }
        }
    });
}
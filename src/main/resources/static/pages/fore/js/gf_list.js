var countryList  = [];
var applyDayList  = [];
var openDayList  = [];
var applyManList  = [];
var createManList  = [];
var nowLawSList  = [];
var zlTypeList  = [];
var ipcList  = [];
var cpecList  = [];
$(function (){
    //左侧菜单
     initlistdata();
     //信息统计
     initleftdata();
     //加载当前位置
     initNowPlace();
     //查询条件
    selctByAllData();
    $("#orderby li").click(function(){//点击事件
        var orderColumn = $(this).attr("value");
        $("#order").val(orderColumn);
        table.ajax.reload();
        selctByAllData();
    });
    $("#normalSearch").click(function () {
        table.ajax.reload();
        selctByAllData();
    });

    $(".LaySearchBtn1").click(function () {
        table.ajax.reload();
        selctByAllData();
        $('#myModal').modal('hide');
    })
    $(".LaySearchBtn2").click(function () {
        $("#applyId").val("");
        $("#applyDay1").val("");
        $("#applyDay2").val("");
        $("#title").val("");
        $("#openDay1").val("");
        $("#openDay2").val("");
        $("#zy").val("");
        $("#openId").val("");
        $("#ipc").val("");
        $("#ipcMC").val("");
        $("#applyMan").val("");
        $("#createMan").val("");
        $("#applyPlace").val("");
        $("#cpec").val("");
        $("#powerAN1").val("");
        $("#powerAN2").val("");
    });
    //加载图表
    initEcharts();
    //加载treetable样式
    initTreetableStyle();
});
var layer;
layui.use([ 'layer' ], function() {
     layer = layui.layer;
});
function selectBy(objName){
    var obj = document.getElementsByName(objName);
    var check_val = [];
    for(var k in obj){
        if(obj[k].checked)
            check_val.push(obj[k].value);
    }
    $("#"+objName).val(check_val);
    table.ajax.reload();
    selctByAllData();
    initleftdata();
    initEcharts();
  //  console.log(check_val);
};
//加载echarts配置及生成
function initEcharts() {
    chartPar1.setOption(option1);
    chartPar2.setOption(option2);
    chartPar3.setOption(option3);
    chartPar4.setOption(option4);
    chartPar5.setOption(option5);
    chartPar6.setOption(option6);
    chartPar7.setOption(option7);
    chartPar8.setOption(option8);
    window.addEventListener("resize", function(){
        this.chartPar1.resize();
        this.chartPar2.resize();
        this.chartPar3.resize();
        this.chartPar4.resize();
        this.chartPar5.resize();
        this.chartPar6.resize();
        this.chartPar7.resize();
        this.chartPar8.resize();
    });
}

function downloadExcel() {

    // var index = layer.load();
    var obj = document.getElementsByName("rowid");
    var check_val = [];
    for (var k in obj) {
        if (obj[k].checked)
            check_val.push(parseInt(obj[k].value));
    }
    if (check_val.length == 0) {
        alert("请选中所导出的数据");
        layer.close(index);
        return false;
    }
    var jsonString;
    if (isCheckAll == false) {
        jsonString = check_val;
    } else {
        jsonString = '';
    }
    location.href = "/zlztDatainfos/downloadExcel?jsonString=" + jsonString+"&fromTable=view_gfdata";
    // layer.close(index);
}
function selctByAllData() {
    var params ={};
    params.order = $("#order").val();
    params.classifyid = $("#classifyid").val();
    params.type = $("#type").val();
    params.searchKey = $("#searchKey").val();
    params.applyId = $("#applyId").val();
    params.applyDay1 = $("#applyDay1").val();
    params.applyDay2 = $("#applyDay2").val();
    params.title = $("#title").val();
    params.openDay1 = $("#openDay1").val();
    params.openDay2 = $("#openDay2").val();
    params.zy = $("#zy").val();
    params.openId = $("#openId").val();
    params.ipc = $("#ipc").val();
    params.ipcMC = $("#ipcMC").val();
    params.applyMan = $("#applyMan").val();
    params.createMan = $("#createMan").val();
    params.applyPlace = $("#applyPlace").val();
    params.cpec = $("#cpec").val();
    params.powerAN1 = $("#powerAN1").val();
    params.powerAN2 = $("#powerAN2").val();
    params.countryId = $("#countryId").val();
    params.applyDayId = $("#applyDayId").val();
    params.openDayId = $("#openDayId").val();
    params.applyManId = $("#applyManId").val();
    params.createManId = $("#createManId").val();
    params.nowLawSId = $("#nowLawSId").val();
    params.zlTypeId = $("#zlTypeId").val();
    params.ipcId = $("#ipcId").val();
    params.cpecId = $("#cpecId").val();
    params.fromTable = "view_gfdata";
    localStorage.setItem("params", JSON.stringify(params));
   // console.log(localStorage);
}
function changData(id,type1) {
    classifyid = id;
    type = type1;
    $("#classifyid").val(classifyid);
    $("#type").val(type);
    //信息统计
    table.ajax.reload();
    initleftdata();
    //加载当前位置
    initNowPlace();
    //查询条件
    selctByAllData();
    //加载图表
    initEcharts();
    //加载treetable样式
    initTreetableStyle();
    $('.fenleiLiTit').parents(".fenleiLi").toggleClass("active");
    $('.fenleiLiTit').parents(".fenleiLi").find(".fenleiSub").slideToggle();
}
function initlistdata() {
    $.ajax({
        url : "/gfClassifyInfos/treetable/"+classifyid+"/"+type,
        type : "get",
        async: false,
        success : function (data) {
          //  console.log(JSON.stringify(data));
            var a = '<div class="fenleiTit">\n' +
                '                        \t<a class="fenleiTitLink" onclick="changData(\''+data.id+'\',\''+data.type+'\')">\n' +
                '                                <h2 class="fenleiTitCn">'+data.name+'</h2>\n' +
                '                                <span class="fenleiTitEn">'+data.translate+'</span>\n' +
                '                            </a>\n' +
                '                        </div>\n' +
                '                        <div class="fenleiH2Btn visible-xs visible-sm visible-md"><i class="fa fa-folder"></i>展开</div>';
            $(".fenleiTop").append(a);
            var result = data.children;
            for (var i in result) {
                var d = result[i];
                var tr = '';
                var td ='';
                if (d.type == 2) {
                    tr = '<tr class="leftTr1"  data-tt-id="'+ d.id + '" data-tt-parent-id="' + d.parentId + '">';
                    td = '<td class="leftLi1"><i><a href="javascript:changData(\''+d.id+'\',\''+d.type+'\')">'+d.name+'</a></i></td>';
                }else if(d.type == 3) {
                    tr = '<tr data-tt-id="'+ d.id + '" data-tt-parent-id="' + d.parentId + '">';
                    td = '<td class="leftLi2"><i><a href="javascript:changData(\''+d.id+'\',\''+d.type+'\')">'+d.name+'</a></i></td>';
                }else if (d.type == 4){
                    tr = '<tr data-tt-id="'+ d.id + '" data-tt-parent-id="' + d.parentId + '">';
                    td = '<td class="leftLi3"><i><a href="javascript:changData(\''+d.id+'\',\''+d.type+'\')">'+d.name+'</a></i></td>';
                }
                tr +=td ;
                tr += '</tr>';
                $(".leftMenu").append(tr);
            }
        }
    })
}

function initleftdata() {
    var obj ={};
    obj.classifyId  =classifyid;
    obj.type  = type;
    obj.country = $("#countryId").val();
    obj.applyDayStr = $("#applyDayId").val();
    obj.openDayStr = $("#openDayId").val();
    obj.applyMan = $("#applyManId").val();
    obj.createMan = $("#createManId").val();
    obj.nowLawS = $("#nowLawSId").val();
    obj.zlType = $("#zlTypeId").val();
    obj.ipc = $("#ipcId").val();
    obj.cpec = $("#cpecId").val();
    $.ajax({
        contentType: "application/json; charset=utf-8",
        url : "/gfDatainfos/getallcount",
        data:JSON.stringify(obj),
        type : "post",
        async: false,
        success : function (data) {
            var result = data;
            //清空统计模块，进行拼接。
            $("#listinfoAll").html('');
           // console.log(result);

            var listdata = '';
            for (var i in result) {
                var d = result[i];
                switch (d.keyname) {
                    case "country" : countryList = d.children;break;
                    case "applyDay" : applyDayList = d.children;break;
                    case "openDay" : openDayList = d.children;break;
                    case "applyMan" : applyManList = d.children;break;
                    case "createMan" : createManList = d.children;break;
                    case "nowLawS" : nowLawSList = d.children;break;
                    case "zlType" : zlTypeList = d.children;break;
                    case "ipc" : ipcList = d.children;break;
                    case "cpec" : cpecList = d.children;break;
                }
                var children = d.children;
                if (d.keyname == 'applyDay'){
                    option1.xAxis.data = [];
                    option1.series[0].data = [];
                    for (var t=children.length-1;t>=0;t--) {
                        option1.xAxis.data.push(children[t].keyname.substring(0,4)+"");
                        option1.series[0].data.push(children[t].count);
                        if (option1.xAxis.data.length >= 20) {
                            break;
                        }
                    }
                }
                if (d.keyname == 'openDay'){
                    option2.xAxis.data = [];
                    option2.series[0].data = [];
                    for (var t=children.length-1;t>=0;t--) {
                        option2.xAxis.data.push(children[t].keyname.substring(0,4)+"");
                        option2.series[0].data.push(children[t].count);
                        if (option2.xAxis.data.length >= 10) {
                            break;
                        }
                    }
                }
                if (d.keyname == 'country'){
                    option3.legend.data = [];
                    option3.series[0].data = [];
                    for (var t in children) {
                        option3.legend.data.push(children[t].keyname);
                        var obj = {
                            value: '',
                            name: ''
                        };
                        obj.value = children[t].count;
                        obj.name = children[t].keyname;
                        option3.series[0].data.push(obj);
                        if (option3.legend.data.length >= 10) {
                            break;
                        }
                    }
                }
                if (d.keyname == 'nowLawS'){
                    option4.legend.data = [];
                    option4.series[0].data = [];
                    for (var t in children) {
                        option4.legend.data.push(children[t].keyname);
                        var obj = {
                            value: '',
                            name: ''
                        };
                        obj.value = children[t].count;
                        obj.name = children[t].keyname;
                        option4.series[0].data.push(obj);
                        if (option4.legend.data.length >= 10) {
                            break;
                        }
                    }
                }
                if (d.keyname == 'createMan'){
                    option5.xAxis[0].data = [];
                    option5.series[0].data = [];
                    for (var t= 0;t<children.length;t++) {
                        option5.xAxis[0].data.push(children[t].keyname);
                        option5.series[0].data.push(children[t].count);
                        if (option5.xAxis[0].data.length >= 10) {
                            break;
                        }
                    }
                }
                if (d.keyname == 'applyMan'){
                    option6.xAxis[0].data = [];
                    option6.series[0].data = [];
                    for (var t= 0;t<children.length;t++) {
                        option6.xAxis[0].data.push(children[t].keyname);
                        option6.series[0].data.push(children[t].count);
                        if (option6.xAxis[0].data.length >= 10) {
                            break;
                        }
                    }
                }
                if (d.keyname == 'ipc'){
                    option7.xAxis[0].data = [];
                    option7.series[0].data = [];
                    for (var t= 0;t<children.length;t++) {
                        option7.xAxis[0].data.push(children[t].keyname);
                        option7.series[0].data.push(children[t].count);
                        if (option7.xAxis[0].data.length >= 10) {
                            break;
                        }
                    }
                }
                if (d.keyname == 'cpec'){
                    option8.xAxis[0].data = [];
                    option8.series[0].data = [];
                    for (var t=0;t<children.length;t++) {
                        option8.xAxis[0].data.push(children[t].keyname);
                        option8.series[0].data.push(children[t].count);
                        if (option8.xAxis[0].data.length >= 10) {
                            break;
                        }
                    }
                }
                listdata += '<li class="fenleiLi"><h3 class="fenleiLiTit"><i class="fenleiIco"></i><a href="#">'+d.name+'</a></h3>';
                if(d.children!==null&& d.children!=""){
                    // console.log(children);

                    listdata += '<div class="fenleiSub siftSub">\n' +
                        '                            \t<ul class="siftList list-unstyled" id="all'+d.keyname+'">';
                    for (var j in children) {
                        if (j < 5) {
                            var p = children[j];
                            if (d.keyname == 'applyDay' || d.keyname == 'openDay') {
                                listdata += '<li><span><input type="checkbox" name="'+d.keyname+'Id" value="'+p.keyname.substring(0,4)+'"><i class="iconfont icon-xuanze"></i><a href="#">'+p.keyname.substring(0,4)+'年('+p.count+')</a></span></li>';
                            }else {
                                listdata += '<li><span><input type="checkbox" name="'+d.keyname+'Id" value="'+p.keyname+'"><i class="iconfont icon-xuanze"></i><a href="#">'+p.keyname+'('+p.count+')</a></li>';
                            }
                        }
                    }
                    listdata += '</ul>\n<div class="siftBtn">\n' +
                        '                            <a href="javascript:selectBy(\''+d.keyname+'Id\');" class="siftBtn1">筛选</a>\n' +
                        '                            <a href="javascript:showAllList(\''+d.keyname+'\');" class="siftBtn2" id="show'+d.keyname+'">展开<i class="iconfont icon-iconfontgengduo"></i></a>\n' +
                        '                        </div>';
                    listdata += '\t</div>';
                }
                listdata += '</li>';
            }
            $("#listinfoAll").append(listdata);
        }
    });
}

function showAllList(objname) {
    var list = objname +"List";
    // if (list == "countryList") {
    //     list = countryList;
    // }
    switch (list) {
        case "countryList" : list = countryList;break;
        case "applyDayList" : list = applyDayList;break;
        case "openDayList" : list = openDayList;break;
        case "applyManList" : list = applyManList;break;
        case "createManList" : list = createManList;break;
        case "nowLawSList" : list = nowLawSList;break;
        case "zlTypeList" : list = zlTypeList;break;
        case "ipcList" : list = ipcList;break;
        case "cpecList" : list = cpecList;break;
    }
    var value =$("#show"+objname).text();
    console.log("aaaaa"+value);
    if (value == "展开") {
        $("#all"+objname).html('');
        for (var i in list) {
            if (objname == 'applyDay' || objname == 'openDay') {
                $("#all"+objname).append('<li><span><input type="checkbox" name="'+list[i].keyname+'Id" value="'+list[i].keyname.substring(0,4)+'"><i class="iconfont icon-xuanze"></i><a href="#">'+list[i].keyname.substring(0,4)+'年('+list[i].count+')</a></span></li>');
            }else {
                $("#all"+objname).append('<li><span><input type="checkbox" name="'+list[i].keyname+'Id" value="'+list[i].keyname+'"><i class="iconfont icon-xuanze"></i><a href="#">'+list[i].keyname+'('+list[i].count+')</a></li>');
            }
        }
        $("#show"+objname).html("隐藏<i class='iconfont icon-iconfontgengduo'></i>");
    }else if(value == "隐藏"){
        $("#all"+objname).html('');
        for (var i in list) {
            if (i<5){
                if (objname == 'applyDay' || objname == 'openDay') {
                    $("#all"+objname).append('<li><span><input type="checkbox" name="'+list[i].keyname+'Id" value="'+list[i].keyname.substring(0,4)+'"><i class="iconfont icon-xuanze"></i><a href="#">'+list[i].keyname.substring(0,4)+'年('+list[i].count+')</a></span></li>');
                }else {
                    $("#all"+objname).append('<li><span><input type="checkbox" name="'+list[i].keyname+'Id" value="'+list[i].keyname+'"><i class="iconfont icon-xuanze"></i><a href="#">'+list[i].keyname+'('+list[i].count+')</a></li>');
                }
            }
        }
        $("#show"+objname).html("展开<i class='iconfont icon-iconfontgengduo'></i>");
    }
}

function initNowPlace() {
    $.ajax({
        url:"/gfClassifyInfos/getNowPlace/"+classifyid+"/"+type,
        type:"get",
        async: false,
        success : function (data) {
            $("#nowPlace").html('');
            $("#nowPlace").html('<li><i class="iconfont icon-weizhi"></i>当前位置：</li>\n' +
                '            <li><a href="#">首页</a></li>\n' +
                '            <li><a href="zlResult.html">高分专区</a></li>');
            for (var i in data) {
                $("#nowPlace").append(' <li><a href="zlzt_list.html?id='+data[i].id+'&type='+data[i].type+'">'+data[i].name+'</a></li>');
            }
        }
    })
}

function initTreetableStyle() {
    $(document).find(".leftMenu").treetable({ expandable: true});
    $(document).find(".leftMenu tbody").on("mousedown", "tr", function() {
        $(".selected").not(this).removeClass("selected");
        $(this).toggleClass("selected");
    });
    $(document).find(".leftMenu .folder").each(function() {
        $(this).parents(".leftMenu tr").droppable({
            accept: ".file, .folder",
            drop: function(e, ui) {
                var droppedEl = ui.draggable.parents("tr");
                $(".leftMenu").treetable("move", droppedEl.data("ttId"), $(this).data("ttId"));
            },hoverClass: "accept",over: function(e, ui) {
                var droppedEl = ui.draggable.parents("tr");
                if(this != droppedEl[0] && !$(this).is(".expanded")) {
                    $(".leftMenu").treetable("expandNode", $(this).data("ttId"));
                }
            }
        });
    });
}

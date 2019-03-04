 // JavaScript Document
jQuery(function () {
	//if(typeof(Worker) == "undefined"){window.location = "noHtml5.html";}
	//window.scrollTo(0,0);
	//imgFull
	
	// $('[data-toggle="popover"]').popover();
	$(".imgFull").each(function(index, element) {
        $(".imgFull").eq(index).css("background-image", "url(" + $(".imgFull").eq(index).find("img").attr("src") + ")");
    });
	$(".noImg").each(function(index, element) {
        if($(this).find("img").attr("src")==""){
			$(this).hide();	
		}
		$(this).find("img").error(function(){
			$(this).parents(".noImg").hide();
		})
    });
	$(".navSearch").click(function(){
		$(".searchBg").show().addClass('fadeInDown animated').removeClass('fadeOutUp');
		$("body").css("overflow","hidden");
	})
	$(".close").click(function(){
		$(".searchBg").removeClass('fadeInDown').addClass('fadeOutUp');
		setTimeout(function(){$(".searchBg").hide();},900);
		$("body").css("overflow","auto");
	})
	$(".banner .swiper-slide").each(function(index){$(this).addClass("ban"+(index+1));});
	var banner = new Swiper('.banner .swiper-container',{
		loop:true,
		//autoplay : 5000,
		autoplayDisableOnInteraction : false,
		speed:800,
		effect:"fade",
		slidesPerView:1,
		observer:true,
		observeParents:true,
		prevButton:'.banPrev',
		nextButton:'.banNext',
		onInit: function(swiper){
			swiperAnimateCache(swiper); 
			swiperAnimate(swiper);
		}, 
		onSlideChangeEnd: function(swiper){
			swiperAnimate(swiper);
		}
	})
	//noticeList
	var noticeList = new Swiper('.noticeList .swiper-container', {
		loop:true,
		autoplay:3000,
		autoplayDisableOnInteraction : false,
		speed:600,
		slidesPerView:1,
		direction : 'vertical',
        observer:true,
        observeParents:true
	});
	$(".box1Item").eq(0).show().addClass('fadeInUp animated').siblings().hide();
	$(".box1Item").each(function() {
        var box1Item = new Swiper($(this).find('.swiper-container'),{
			loop:true,
			slidesPerView:3,
			spaceBetween:30,
			resizeReInit:true,			
			observer:true,
			observeParents:true,
			prevButton: $(this).find('.box1Prev'),
			nextButton: $(this).find('.box1Next'),
			breakpoints: {
				1600: {
					slidesPerView:3,
					spaceBetween:20,
				},
				1400: {
					slidesPerView:3,
					spaceBetween:15,
				},
				1200: {
					slidesPerView:3,
					spaceBetween:10,
				},
				991: {
					slidesPerView:2,
					spaceBetween:20,
				},
				767: {
					slidesPerView:2,
					spaceBetween:10,
				},
				580: {
					slidesPerView:1,
					spaceBetween:15,
				}
			}
		})
    });
	$(".box1Tab li span").click(function(){
		$(this).parent().addClass("active").siblings().removeClass("active");
		$(".box1Bot .box1Item").eq($(this).parent().index()).show().addClass('fadeInUp animated').siblings().hide();
	})	
	$(document).on("click",".upcollect a",function(event){
		$(this).addClass("active");	
	})
	$(".box2Rig li").each(function(index){$(this).attr("data-wow-delay",index/10+"s");});
	$(".box4List li").each(function(index){$(this).attr("data-wow-delay",index/10+"s");});
	$(".box5Item").each(function(index, element) {
        $(this).find("li").each(function(index, element) {
            $(this).attr("data-wow-delay",index/10+"s");
        });
    });
	var box5List = new Swiper('.box5List .swiper-container', {
		loop:true,
		slidesPerView:1,
		resizeReInit:true,	
        observer:true,
        observeParents:true,
		prevButton:'.box5Prev',
		nextButton:'.box5Next',
	});
	var box6List = new Swiper('.box6List .swiper-container', {
		slidesPerView:4,
		slidesPerColumn : 2,
		spaceBetween:30,
		resizeReInit:true,	
        observer:true,
        observeParents:true,
		pagination:'.box6List .box6Page',
		paginationClickable:true,	
		breakpoints: {
			1200: {
				slidesPerView:4,
				spaceBetween:20,
			},
			991: {
				slidesPerView:2,
				spaceBetween:20,
			},
			767: {
				slidesPerView:2,
				spaceBetween:10,
			},
			460: {
				slidesPerView:1,
				spaceBetween:15,
			}
		}	
	});	
	
	//foot
	$(".ftBotIco li:first-child").click(function(){
		$(this).toggleClass("active");
	})
	function resourceList(){
		$(".resourceList > li").each(function(index, element) {
			if($(this).find(".resourceBot").height()>$(this).find(".resourceBotBox").height()){
				$(this).find(".resourceMore").show();
			}else{
				$(this).find(".resourceMore").hide();
			}
			if($(this).index()%5==0){
				$(this).addClass("resoureLi1");	
			}else if($(this).index()%5==1){
				$(this).addClass("resoureLi2");
			}else if($(this).index()%5==2){
				$(this).addClass("resoureLi3");
			}else if($(this).index()%5==3){
				$(this).addClass("resoureLi4");
			}else if($(this).index()%5==4){
				$(this).addClass("resoureLi5");
			}
		});	
	}
	resourceList();
	$(".resourceMore").click(function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			$(this).find("i").removeClass("icon-iconjian");
			$(this).parents(".resourceBox").removeClass("active");
			$(this).prev().animate({height:"90px"});
		}else{
			$(this).addClass("active");
			$(this).find("i").addClass("icon-iconjian");
			$(this).parents(".resourceBox").addClass("active");
			var resourceHig = $(this).parents(".resourceBox").find(".resourceBot").height();
			$(this).prev().animate({height:resourceHig});
		}
	})
	 //left
	if($(".leftMenu").length>0){
		$(document).find(".leftMenu").treetable({ expandable: true});
	}
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
	
	$(document).on("click",".fenleiH2Btn",function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			$(this).html("<i class='fa fa-folder'></i>展开");
			$(this).parent().next(".fenleiList").slideUp();
		}else{
			$(this).addClass("active");
			$(this).html("<i class='fa fa-folder-open'></i>关闭");
			$(this).parent().next(".fenleiList").slideDown();
		}
	})
	$(document).find(".fenleiLi").each(function(index, element) {
        if($(this).find(".fenleiSub").length>0){
			$(this).find(".fenleiLiTit a").attr("href","javascript:;");
		}else{
			$(this).find(".fenleiIco").hide();	
		}
    });
	$(document).find(".fenleiLiSub").each(function(index, element) {
        if($(this).find(".fenleiThree").length>0){
			$(this).find(".fenleiLiSubTit").attr("href","javascript:;");
		}
    });
	$(document).on("click",".fenleiLiTit",function(){
		if($(this).parents(".fenleiLi").find(".fenleiSub").length>0){
			$(this).parents(".fenleiLi").toggleClass("active");
			$(this).parents(".fenleiLi").find(".fenleiSub").slideToggle();
		}else{
				
		}
	})
	$(document).on("click",".fenleiLiSubTit",function(){
		if($(this).parents(".fenleiLiSub").find(".fenleiThree").length>0){
			$(this).parents(".fenleiLiSub").toggleClass("active");
			$(this).parents(".fenleiLiSub").find(".fenleiThree").slideToggle();	
		}
	})
	$(document).on("click",".checkIco",function(){
		$(this).toggleClass("icon-p");	
	})
	$(".dingyue").click(function(){
		$(this).addClass("active").html("已订阅");	
	})
	//select
	$(document).on("click",".selTit",function(event){
		$(".selList").stop().hide();
		$(".selList").removeClass("active");
		$(this).next(".selList").stop().show();
		event.stopPropagation();
	})
	$(document).on("click",".selList li",function(){
		$(".selList").stop().hide();
		$(this).parent(".selList").prev(".selTit").html($(this).html());
		$(this).parent(".selList").next("input").val($(this).attr("data-index"));
	})
	$(window).on("click",function(){
		$(".inputSelect").removeClass("active");
		$(".selList").stop().hide();
	})
	
	//专项成果
	var cgBox1Rig = new Swiper('.cgBox1Rig .swiper-container', {
		spaceBetween: 10,
		slidesPerView:1,
		effect:"fade",
        observer:true,
        observeParents:true,
		autoHeight:true,
		onSlideChangeEnd: function(swiper){
			$(".cgBox1Tab li").eq(swiper.realIndex).addClass("active").siblings().removeClass("active");
    	}
	});
	$(".cgBox1Tab li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		var cliNum = $(this).index();
		cgBox1Rig.slideTo(cliNum, 1000, false);
	})	
	var cgBox3 = new Swiper('.cgBox3 .swiper-container', {
		loop:true,
		spaceBetween: 10,
		slidesPerView:4,
        observer:true,
        observeParents:true,
		autoHeight:true,
		prevButton:'.cgBox3Prev',
		nextButton:'.cgBox3Next',
		breakpoints: {
			1200: {
				slidesPerView:3,
				spaceBetween:10,
			},
			991: {
				slidesPerView:2,
				spaceBetween:20,
			},
			767: {
				slidesPerView:2,
				spaceBetween:10,
			},
			460: {
				slidesPerView:1,
				spaceBetween:15,
			}
		}
	});	
	var cgBox5Bot = new Swiper('.cgBox5Bot .swiper-container', {
		spaceBetween: 10,
		slidesPerView:1,
		effect:"fade",
        observer:true,
        observeParents:true,
		autoHeight:true,
		onSlideChangeEnd: function(swiper){
			$(".cgBox5Tab li").eq(swiper.realIndex).addClass("active").siblings().removeClass("active");
    	}
	});
	$(".cgBox5Tab li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		var cliNum = $(this).index();
		cgBox5Bot.slideTo(cliNum, 1000, false);
	})	
	var cgBox6List = new Swiper('.cgBox6List .swiper-container', {
		slidesPerView:2,
		spaceBetween:40,
		resizeReInit:true,	
        observer:true,
        observeParents:true,
		pagination:'.cgBox6List .box6Page',
		paginationClickable:true,	
		breakpoints: {
			1200: {
				slidesPerView:2,
				spaceBetween:20,
			},
			991: {
				slidesPerView:1,
				spaceBetween:10,
			}
		}	
	});
	//科技商城-筛选
	function sxBtn(){
		$(".sxBotItem").each(function(index, element) {
			if($(this).find(".sxFirCon").height()>34){
				$(this).find(".sxBtn").show();
			}else{
				$(this).find(".sxBtn").hide();
			}
		});
	}
	sxBtn();
	function sxTopSize(){
		if($(".sxTopCon .sxTag").length>0){
			$(".delSelBtn").css("display","inline-block");
		}else{
			$(".delSelBtn").css("display","none");	
		}
	}
	$(document).on("click",".sxBtn",function(event){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			$(this).html("展开<i class='fa fa-caret-down'></i>");
			$(this).prev().find(".sxFirst").removeClass("active");
			$(this).prev().find(".sxSecond").hide();
		}else{
			$(this).addClass("active");
			$(this).html("收起<i class='fa fa-caret-up'></i>");
			$(this).prev().find(".sxFirst").addClass("active");
		}
	})
	$(document).on("click",".sxFirst a",function(event){
		var sxTit = $(this).parents(".sxBotItem").find(".sxBotTit").text(),
			sxCon = $(this).text(),
			sxId = $(this).parents(".sxBotItem").index()+1,
			sxTagId = "sxTag" + sxId,
			sxAdd = "<span class='sxTag " + sxTagId + "'><b>" + sxTit + "</b><em><span>"+ sxCon + "</span></em><i class='iconfont icon-guanbi'></i></span>";
		if($(this).hasClass("active")){
			return;
		}else{
			$(this).addClass("active").siblings().removeClass("active");
			var parNum = $(this).attr("data-index");
			$(this).parents(".sxFirst").nextAll(".sxSecond").hide();
			$(this).parents(".sxFirst").nextAll(".sxSecond").each(function(index, element) {				
                if($(this).attr("data-con")==parNum){
					$(this).show().siblings(".sxSecond").hide();	
				}
            });			
			if($(".sxTopCon").children().hasClass(sxTagId)){
				$('.'+sxTagId).find("em").html('<span>'+sxCon+'</span>');				
			}else{
				$(".sxTopCon").prepend(sxAdd);	
			}
			$(this).parents(".sxFirst").nextAll(".sxSecond").find("a").removeClass('active');
		}
		if($(this).attr("data-index")==0){
			$('.'+sxTagId).remove();
			$('.sxSecond').hide();
		}
		sxTopSize();
	})
	$(document).on("click",".sxSecond a",function(event){
		if($(this).hasClass("active")){
			return;
		}else{
			$(this).addClass('active').siblings().removeClass('active');
			var sxId = $(this).parents(".sxBotItem").index()+1;
			var old=$('.sxTag'+sxId).find("em span").html();
			var now=$(this).html();
			$('.sxTag'+sxId).find("em").html('<span>'+ old + '</span> - '+now);
		}
    })
	$(document).on("click",".sxTopCon > .sxTag",function(event){
		var clickNum = $(this).attr("class").match(/\d+/g);
		$(this).remove();
		$('.sxBotItem').eq(clickNum-1).find('a.active').removeClass('active');
		$('.sxBotItem').eq(clickNum-1).find(".sxFirCon [data-index='0']").addClass("active");
		$('.sxSecond').hide();
		sxTopSize();
    })	
	$(document).on("click",".delSelBtn",function(event){
		$(".sxTopCon").find(".sxTag").remove();
		$('.sxBotItem').find('a.active').removeClass('active');
		$('.sxBotItem').find(".sxFirCon [data-index='0']").addClass("active");
		$('.sxSecond').hide();
		$(this).hide();
	})
	//科技商城详情	
	if($(".technoImgTop .swiper-slide").length>1){
		var technoImgTop = new Swiper('.technoImgTop .swiper-container', {
			spaceBetween: 10,
			loop:true,
			loopedSlides: 7,
			roundLengths:true,
			prevButton: '.technoImgTop .technoPrev',
			nextButton: '.technoImgTop .technoNext',
			pagination:'.technoPage',
			paginationClickable:true,
		});
		var technoImgBot = new Swiper('.technoImgBot .swiper-container', {
			prevButton: '.technoImgBot .technoPrev',
			nextButton: '.technoImgBot .technoNext',
			spaceBetween: 10,
			slidesPerView:3,
			touchRatio: 0.2,
			loop:true,
			loopedSlides: 7,
			slideToClickedSlide: true,
			breakpoints: {
				640: {
					slidesPerView:3,
				}
			}
		});
		technoImgTop.params.control = technoImgBot;
		technoImgBot.params.control = technoImgTop;
	}
	//科技商城锚点	
	$(".anchorPoint li a").click(function(){
		$('html,body').animate({scrollTop:$(".anchorItem").eq($(this).parent().index()).offset().top-$(".logo").height()-30}, 800);
	})
	//交易流程	
	$(".trading li").each(function(index){$(this).attr("data-wow-delay",index/10+"s");});
	//专题报告
	$(".reportBot .reportItem:first-child").show();
	$(".reportNav li:not(:last-child) a").click(function(){
		$(this).addClass("active").parent().siblings().find("a").removeClass("active");	
		$(this).parents(".reportTop").next(".reportBot").find(".reportItem").eq($(this).parent().index()).addClass("animated fadeInUp").show().siblings().hide();
	})
	
		
	function equip() {
		$("nav.navbar.bootsnav.no-full .navbar-collapse").css("max-height",$(window).height()-$(".logo").height());
		var sUserAgent = navigator.userAgent.toLowerCase(); 
		if((sUserAgent.match(/(ipod|iphone os|midp|ucweb|android|windows ce|windows mobile)/i))){
		}else{
		}		
	}
	equip();
    $(window).resize(function(){
		equip();
		resourceList();
		sxBtn()
    })
})
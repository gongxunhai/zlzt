 // JavaScript Document
jQuery(function () {
	//if(typeof(Worker) == "undefined"){window.location = "noHtml5.html";}
	//window.scrollTo(0,0);
	//imgFull
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
	$(".fenleiH2Btn").on("click",function(){
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
	$(".fenleiLi").each(function(index, element) {
        if($(this).find(".fenleiSub").length>0){
			$(this).find(".fenleiLiTit a").attr("href","javascript:;");
		}else{
			$(this).find(".fenleiIco").hide();	
		}
    });
	$(".fenleiLiSub").each(function(index, element) {
        if($(this).find(".fenleiThree").length>0){
			$(this).find(".fenleiLiSubTit").attr("href","javascript:;");
		}
    });
	$(".fenleiLiTit").click(function(){
		if($(this).parents(".fenleiLi").find(".fenleiSub").length>0){
			$(this).parents(".fenleiLi").toggleClass("active");
			$(this).parents(".fenleiLi").find(".fenleiSub").slideToggle();
		}else{
				
		}
	})
	$(".fenleiLiSubTit").click(function(){
		if($(this).parents(".fenleiLiSub").find(".fenleiThree").length>0){
			$(this).parents(".fenleiLiSub").toggleClass("active");
			$(this).parents(".fenleiLiSub").find(".fenleiThree").slideToggle();	
		}
	})
	$(".checkIco").click(function(){
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
    })
})
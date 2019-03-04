layui.use(['layer', 'form'], function() {


  console.log('切换头部');
  // 进入评分
  $('.score').on('click', function() {
    console.log('先登录再评分');
  });
  // 登录
  $('.login').on('click', function() {
    console.log('出现登录框');
  });




})



 //配置图表路径
                                  require.config({
                                      paths: {
                                          echarts: 'http://echarts.baidu.com/build/dist'
                                      }
                                  });
                                  //加载图表js文件
                                  require(
                                      [
                                          'echarts',
                                          'echarts/chart/radar', //要什么图表类型配置什么类型
                                      ],
                                      function(ec) {
                                          //基于准备好的dom，初始化echarts图表
                                          var myChart = ec.init(document.getElementById('main2'));
                                          var option = {
                                              polar: [{
                                                  indicator: [{
                                                          text: '销售',
                                                          max: 6500
                                                      },
                                                      {
                                                          text: '管理',
                                                          max: 16000
                                                      },
                                                      {
                                                          text: '信息技术',
                                                          max: 30000
                                                      },
                                                      {
                                                          text: '客服',
                                                          max: 38000
                                                      },
                                                      {
                                                          text: '研发',
                                                          max: 52000
                                                      },
                                                      {
                                                          text: '市场',
                                                          max: 25000
                                                      }
                                                  ]
                                              }],
                                              series: [{
                                                  name: "",
                                                  type: "radar",
                                                  data: [{
                                                      value: [4300, 10000, 28000, 35000, 50000, 19000],
                                                      name: "预算分配"
                                                  }],
                                                  itemStyle: {
                                                      normal: {
                                                          color: "#1DBB37"
                                                      }
                                                  }
                                              }, {
                                                  name: "",
                                                  type: "radar",
                                                  data: [{
                                                      value: [5000, 14000, 28000, 31000, 42000, 21000],
                                                      name: "实际开销"
                                                  }],
                                                  itemStyle: {
                                                      normal: {
                                                          color: "rgb(51, 153, 254)"
                                                      }
                                                  }
                                              }],
                                          };

                                          var ecConfig = require('echarts/config');
                                          myChart.on(ecConfig.EVENT.CLICK, function(param) {
                                              //点击后执行操作
                                              alert(param.name)
                                          });
                                          myChart.setOption(option);
                                      });

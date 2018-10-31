var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
option = {
    title: {
        text: 'Graph 简单示例'
    },
    tooltip: {},
    animationDurationUpdate: 1500,
    animationEasingUpdate: 'quinticInOut',
    series: [
        {
            type: 'graph',
            layout: 'none',
            symbolSize: 50,
            roam: true,
            label: {
                normal: {
                    show: true
                }
            },
            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [4, 10],
            edgeLabel: {
                normal: {
                    textStyle: {
                        fontSize: 20
                    }
                }
            },
            data: [{
                name: '用户',
                x: 550,
                y: 150
            },
                {
                    name: 'web',
                    x: 550,
                    y: 200
                }
                ,
                {
                    name: 'service',
                    x: 550,
                    y: 270
                }
                ,
                {
                    name: 'mysql',
                    x: 550,
                    y: 320
                }
                ,
                {
                    name: 'mongodb',
                    x: 450,
                    y: 200
                }
                ,
                {
                    name: 'redis',
                    x: 650,
                    y: 200
                }
                ,
                {
                    name: '对象桶',
                    x: 470,
                    y: 250
                }
                ,
                {
                    name: '队列',
                    x: 620,
                    y: 250
                }
            ],
            // links: [],
            links: [{
                source: '用户',
                target: 'web'
            }, {
                source: 'web',
                target: 'redis'
            }, {
                source: 'web',
                target: 'mongodb'
            }, {
                source: 'web',
                target: '对象桶'
            }, {
                source: 'web',
                target: '队列'
            }, {
                source: 'web',
                target: 'service'
            }, {
                source: '队列',
                target: 'service'
            }, {
                source: 'service',
                target: 'mysql'
            }

            ],
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 2,
                    curveness: 0
                }
            }
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
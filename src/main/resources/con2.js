var Stomp = require('stompjs');
var SockJS = require('sockjs-client');

const argv = process.argv;


var url = "http://localhost:8081/gs-guide-websocket"
// 建立连接对象（还未发起连接）
var socket = new SockJS(url);
// 获取 STOMP 子协议的客户端对象
var stompClient = Stomp.over(socket);

var number = 1;
console.log('=====准备连接====');
stompClient.connect(
    {},//可添加客户端的认证信息
    function connectCallback() {//连接成功的回调函数
        stompClient.send("/app/hello", {}, '{"name": "daluo"}')
        // stompClient.send("/app/hello", {}, JSON.stringify({'name': 'xiaoluo'}));
        console.log('=====常连接====');
        console.log(argv[2]);
        //订阅频道
        // stompClient.subscribe('/topic/greetings', function (data) {
        stompClient.subscribe(argv[2], function (data) {
            if (data) {
                console.log('client %s subscribe data from %s: %o', argv[3], argv[2], data);
            }
        });

        stompClient.subscribe(argv[3], function (data) {
            if (data) {
                console.log('client %s subscribe data from %s: %o', argv[3], argv[3], data);
            }
        });
    },
    function errorCallBack(error) {
        console.log('error', error);
    }
)



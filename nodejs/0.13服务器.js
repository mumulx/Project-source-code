var http = require("http");
var fs = require("fs");
var path = require('path');
var server = http.createServer();
server.on('request',function(req,res){
/*	if(req.url==="/"||req.url==="/index.html"){
		fs.readFile("./index.html",function(err,data){
			res.writeHead(200,{"Content-Type":"text/html;charset=utf-8"});
			res.write(data);
			res.end();
		});
	}
	if(req.url==="/css/index.css"){
		fs.readFile("./css/index.css",function(err,data){
			res.writeHead(200,{"Content-Type":"text/css;charset=utf-8"});
			res.write(data);
			res.end();
		});
	}*/
	if(req.url==='/'){
		req.url='/index.html'; //制作一一个默认首页
	}
	//console.log(req.url);
	var fn="./"+req.url;
	var ext = path.extname(fn);
	var exts = {".html":"text/html",".css":"text/css",".png":"image/png"};
	fs.readFile (fn,function (err,data) {
		if(err){
			res.writeHead(404,{"Content-Type":exts[ext]+";charset=utf-8"});
			res.write ("资源找不到") ;
			res.end() ;	
		}else{
			res.writeHead(200,{"Content-Type":exts[ext]+";charset=utf-8"});
			res.write (data) ;
			res.end() ;		
		}
}) ;
});
server.listen(80,function(){
	console.log("Server is runing ...");
});

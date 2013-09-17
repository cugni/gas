(function(){var c="2.0.0-javascript",a={},d,f=[],e=[],b=Object.prototype.hasOwnProperty;
a={onError:function(g){},onClose:function(g){},onOpen:function(g){},onReopen:function(g){},onMessage:function(g){},onReconnect:function(h,g){},onMessagePublished:function(g){},onTransportFailure:function(h,g){},onLocalMessage:function(g){},onFailureToReconnect:function(h,g){},AtmosphereRequest:function(K){var M={timeout:300000,method:"GET",headers:{},contentType:"",callback:null,url:"",data:"",suspend:true,maxRequest:-1,reconnect:true,maxStreamingLength:10000000,lastIndex:0,logLevel:"info",requestCount:0,fallbackMethod:"GET",fallbackTransport:"streaming",transport:"long-polling",webSocketImpl:null,webSocketBinaryType:null,dispatchUrl:null,webSocketPathDelimiter:"@@",enableXDR:false,rewriteURL:false,attachHeadersAsQueryString:true,executeCallbackBeforeReconnect:false,readyState:0,lastTimestamp:0,withCredentials:false,trackMessageLength:false,messageDelimiter:"|",connectTimeout:-1,reconnectInterval:0,dropAtmosphereHeaders:true,uuid:0,async:true,shared:false,readResponsesHeaders:false,maxReconnectOnClose:5,enableProtocol:true,onError:function(ax){},onClose:function(ax){},onOpen:function(ax){},onMessage:function(ax){},onReopen:function(ay,ax){},onReconnect:function(ay,ax){},onMessagePublished:function(ax){},onTransportFailure:function(ay,ax){},onLocalMessage:function(ax){},onFailureToReconnect:function(ay,ax){}};
var U={status:200,reasonPhrase:"OK",responseBody:"",messages:[],headers:[],state:"messageReceived",transport:"polling",error:null,request:null,partialMessage:"",errorHandled:false,id:0};
var X=null;
var n=null;
var t=null;
var C=null;
var E=null;
var ai=true;
var k=0;
var au=false;
var Y=null;
var ao;
var p=null;
var H=a.util.now();
var I;
aw(K);
function ap(){ai=true;
au=false;
k=0;
X=null;
n=null;
t=null;
C=null
}function y(){ak();
ap()
}function J(ay,ax){if(U.partialMessage===""&&(ax.transport==="streaming")&&(ay.responseText.length>ax.maxStreamingLength)){U.messages=[];
ag(true);
B();
ak();
P(ay,ax,0)
}}function B(){if(M.enableProtocol&&!M.firstMessage){var ay="X-Atmosphere-Transport=close&X-Atmosphere-tracking-id="+M.uuid;
var ax=M.url.replace(/([?&])_=[^&]*/,ay);
ax=ax+(ax===M.url?(/\?/.test(M.url)?"&":"?")+ay:"");
M.attachHeadersAsQueryString=false;
M.dropAtmosphereHeaders=true;
M.url=ax;
M.transport="polling";
m("",M)
}}function al(){M.reconnect=false;
au=true;
U.request=M;
U.state="unsubscribe";
U.responseBody="";
U.status=408;
U.partialMessage="";
A();
B();
ak()
}function ak(){U.partialMessage="";
if(M.id){clearTimeout(M.id)
}if(C!=null){C.close();
C=null
}if(E!=null){E.abort();
E=null
}if(t!=null){t.abort();
t=null
}if(X!=null){if(X.webSocketOpened){X.close()
}X=null
}if(n!=null){n.close();
n=null
}aq()
}function aq(){if(ao!=null){clearInterval(I);
document.cookie=encodeURIComponent("atmosphere-"+M.url)+"=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
ao.signal("close",{reason:"",heir:!au?H:(ao.get("children")||[])[0]});
ao.close()
}if(p!=null){p.close()
}}function aw(ax){y();
M=a.util.extend(M,ax);
M.mrequest=M.reconnect;
if(!M.reconnect){M.reconnect=true
}}function o(){return M.webSocketImpl!=null||window.WebSocket||window.MozWebSocket
}function Q(){return window.EventSource
}function r(){if(M.shared){p=af(M);
if(p!=null){if(M.logLevel==="debug"){a.util.debug("Storage service available. All communication will be local")
}if(p.open(M)){return
}}if(M.logLevel==="debug"){a.util.debug("No Storage service available.")
}p=null
}M.firstMessage=true;
M.isOpen=false;
M.ctime=a.util.now();
if(M.transport!=="websocket"&&M.transport!=="sse"){q(M)
}else{if(M.transport==="websocket"){if(!o()){O("Websocket is not supported, using request.fallbackTransport ("+M.fallbackTransport+")")
}else{ah(false)
}}else{if(M.transport==="sse"){if(!Q()){O("Server Side Events(SSE) is not supported, using request.fallbackTransport ("+M.fallbackTransport+")")
}else{G(false)
}}}}}function af(aB){var aC,aA,aF,ax="atmosphere-"+aB.url,ay={storage:function(){if(!a.util.supportStorage()){return
}var aI=window.localStorage,aG=function(aJ){return a.util.parseJSON(aI.getItem(ax+"-"+aJ))
},aH=function(aJ,aK){aI.setItem(ax+"-"+aJ,a.util.stringifyJSON(aK))
};
return{init:function(){aH("children",aG("children").concat([H]));
a.util.on("storage.socket",function(aJ){aJ=aJ.originalEvent;
if(aJ.key===ax&&aJ.newValue){az(aJ.newValue)
}});
return aG("opened")
},signal:function(aJ,aK){aI.setItem(ax,a.util.stringifyJSON({target:"p",type:aJ,data:aK}))
},close:function(){var aJ=aG("children");
a.util.off("storage.socket");
if(aJ){if(aD(aJ,aB.id)){aH("children",aJ)
}}}}
},windowref:function(){var aG=window.open("",ax.replace(/\W/g,""));
if(!aG||aG.closed||!aG.callbacks){return
}return{init:function(){aG.callbacks.push(az);
aG.children.push(H);
return aG.opened
},signal:function(aH,aI){if(!aG.closed&&aG.fire){aG.fire(a.util.stringifyJSON({target:"p",type:aH,data:aI}))
}},close:function(){if(!aF){aD(aG.callbacks,az);
aD(aG.children,H)
}}}
}};
function aD(aJ,aI){var aG,aH=aJ.length;
for(aG=0;
aG<aH;
aG++){if(aJ[aG]===aI){aJ.splice(aG,1)
}}return aH!==aJ.length
}function az(aG){var aI=a.util.parseJSON(aG),aH=aI.data;
if(aI.target==="c"){switch(aI.type){case"open":L("opening","local",M);
break;
case"close":if(!aF){aF=true;
if(aH.reason==="aborted"){al()
}else{if(aH.heir===H){r()
}else{setTimeout(function(){r()
},100)
}}}break;
case"message":D(aH,"messageReceived",200,aB.transport);
break;
case"localMessage":aa(aH);
break
}}}function aE(){var aG=new RegExp("(?:^|; )("+encodeURIComponent(ax)+")=([^;]*)").exec(document.cookie);
if(aG){return a.util.parseJSON(decodeURIComponent(aG[2]))
}}aC=aE();
if(!aC||a.util.now()-aC.ts>1000){return
}aA=ay.storage()||ay.windowref();
if(!aA){return
}return{open:function(){var aG;
I=setInterval(function(){var aH=aC;
aC=aE();
if(!aC||aH.ts===aC.ts){az(a.util.stringifyJSON({target:"c",type:"close",data:{reason:"error",heir:aH.heir}}))
}},1000);
aG=aA.init();
if(aG){setTimeout(function(){L("opening","local",aB)
},50)
}return aG
},send:function(aG){aA.signal("send",aG)
},localSend:function(aG){aA.signal("localSend",a.util.stringifyJSON({id:H,event:aG}))
},close:function(){if(!au){clearInterval(I);
aA.signal("close");
aA.close()
}}}
}function ab(){var ay,ax="atmosphere-"+M.url,aC={storage:function(){if(!a.util.supportStorage()){return
}var aD=window.localStorage;
return{init:function(){a.util.on("storage.socket",function(aE){aE=aE.originalEvent;
if(aE.key===ax&&aE.newValue){az(aE.newValue)
}})
},signal:function(aE,aF){aD.setItem(ax,a.util.stringifyJSON({target:"c",type:aE,data:aF}))
},get:function(aE){return a.util.parseJSON(aD.getItem(ax+"-"+aE))
},set:function(aE,aF){aD.setItem(ax+"-"+aE,a.util.stringifyJSON(aF))
},close:function(){a.util.off("storage.socket");
aD.removeItem(ax);
aD.removeItem(ax+"-opened");
aD.removeItem(ax+"-children")
}}
},windowref:function(){var aE=ax.replace(/\W/g,""),aD=document.getElementById(aE),aF;
if(!aD){aD=document.createElement("div");
aD.id=aE;
aD.style.display="none";
aD.innerHTML='<iframe name="'+aE+'" />';
document.body.appendChild(aD)
}aF=aD.firstChild.contentWindow;
return{init:function(){aF.callbacks=[az];
aF.fire=function(aG){var aH;
for(aH=0;
aH<aF.callbacks.length;
aH++){aF.callbacks[aH](aG)
}}
},signal:function(aG,aH){if(!aF.closed&&aF.fire){aF.fire(a.util.stringifyJSON({target:"c",type:aG,data:aH}))
}},get:function(aG){return !aF.closed?aF[aG]:null
},set:function(aG,aH){if(!aF.closed){aF[aG]=aH
}},close:function(){}}
}};
function az(aD){var aF=a.util.parseJSON(aD),aE=aF.data;
if(aF.target==="p"){switch(aF.type){case"send":aj(aE);
break;
case"localSend":aa(aE);
break;
case"close":al();
break
}}}Y=function aB(aD){ay.signal("message",aD)
};
function aA(){document.cookie=encodeURIComponent(ax)+"="+encodeURIComponent(a.util.stringifyJSON({ts:a.util.now()+1,heir:(ay.get("children")||[])[0]}))
}ay=aC.storage()||aC.windowref();
ay.init();
if(M.logLevel==="debug"){a.util.debug("Installed StorageService "+ay)
}ay.set("children",[]);
if(ay.get("opened")!=null&&!ay.get("opened")){ay.set("opened",false)
}aA();
I=setInterval(aA,1000);
ao=ay
}function L(az,aC,ay){if(M.shared&&aC!=="local"){ab()
}if(ao!=null){ao.set("opened",true)
}ay.close=function(){al()
};
if(k>0&&az==="re-connecting"){ay.isReopen=true;
ac(U)
}else{if(U.error==null){U.request=ay;
var aA=U.state;
U.state=az;
var ax=U.transport;
U.transport=aC;
var aB=U.responseBody;
A();
U.responseBody=aB;
U.state=aA;
U.transport=ax
}}}function x(az){az.transport="jsonp";
var ay=M,ax;
if((az!=null)&&(typeof(az)!=="undefined")){ay=az
}E={open:function(){var aB="atmosphere"+(++H);
function aA(){var aC=ay.url;
if(ay.dispatchUrl!=null){aC+=ay.dispatchUrl
}var aE=ay.data;
if(ay.attachHeadersAsQueryString){aC=V(ay);
if(aE!==""){aC+="&X-Atmosphere-Post-Body="+encodeURIComponent(aE)
}aE=""
}var aD=document.head||document.getElementsByTagName("head")[0]||document.documentElement;
ax=document.createElement("script");
ax.src=aC+"&jsonpTransport="+aB;
ax.clean=function(){ax.clean=ax.onerror=ax.onload=ax.onreadystatechange=null;
if(ax.parentNode){ax.parentNode.removeChild(ax)
}};
ax.onload=ax.onreadystatechange=function(){if(!ax.readyState||/loaded|complete/.test(ax.readyState)){ax.clean()
}};
ax.onerror=function(){ax.clean();
ad(0,"maxReconnectOnClose reached")
};
aD.insertBefore(ax,aD.firstChild)
}window[aB]=function(aE){if(ay.reconnect){if(ay.maxRequest===-1||ay.requestCount++<ay.maxRequest){if(!ay.executeCallbackBeforeReconnect){P(E,ay,0)
}if(aE!=null&&typeof aE!=="string"){try{aE=aE.message
}catch(aD){}}var aC=v(aE,ay,U);
if(!aC){D(U.responseBody,"messageReceived",200,ay.transport)
}if(ay.executeCallbackBeforeReconnect){P(E,ay,0)
}}else{a.util.log(M.logLevel,["JSONP reconnect maximum try reached "+M.requestCount]);
ad(0,"maxRequest reached")
}}};
setTimeout(function(){aA()
},50)
},abort:function(){if(ax.clean){ax.clean()
}}};
E.open()
}function i(ax){if(M.webSocketImpl!=null){return M.webSocketImpl
}else{if(window.WebSocket){return new WebSocket(ax)
}else{return new MozWebSocket(ax)
}}}function j(){return a.util.getAbsoluteURL(V(M)).replace(/^http/,"ws")
}function av(){var ax=V(M);
return ax
}function G(ay){U.transport="sse";
var ax=av(M.url);
if(M.logLevel==="debug"){a.util.debug("Invoking executeSSE");
a.util.debug("Using URL: "+ax)
}if(M.enableProtocol&&ay){var aA=a.util.now()-M.ctime;
M.lastTimestamp=Number(M.stime)+Number(aA)
}if(ay&&!M.reconnect){if(n!=null){ak()
}return
}try{n=new EventSource(ax,{withCredentials:M.withCredentials})
}catch(az){ad(0,az);
O("SSE failed. Downgrading to fallback transport and resending");
return
}if(M.connectTimeout>0){M.id=setTimeout(function(){if(!ay){ak()
}},M.connectTimeout)
}n.onopen=function(aB){w(M);
if(M.logLevel==="debug"){a.util.debug("SSE successfully opened")
}if(!M.enableProtocol){if(!ay){L("opening","sse",M)
}else{L("re-opening","sse",M)
}}ay=true;
if(M.method==="POST"){U.state="messageReceived";
n.send(M.data)
}};
n.onmessage=function(aC){w(M);
if(!M.enableXDR&&aC.origin&&aC.origin!==window.location.protocol+"//"+window.location.host){a.util.log(M.logLevel,["Origin was not "+window.location.protocol+"//"+window.location.host]);
return
}U.state="messageReceived";
U.status=200;
aC=aC.data;
var aB=v(aC,M,U);
if(n.URL){n.interval=100;
n.URL=av(M.url)
}if(!aB){A();
U.responseBody="";
U.messages=[]
}};
n.onerror=function(aB){clearTimeout(M.id);
ag(ay);
ak();
if(au){a.util.log(M.logLevel,["SSE closed normally"])
}else{if(!ay){O("SSE failed. Downgrading to fallback transport and resending")
}else{if(M.reconnect&&(U.transport==="sse")){if(k++<M.maxReconnectOnClose){L("re-connecting",M.transport,M);
M.id=setTimeout(function(){G(true)
},M.reconnectInterval);
U.responseBody="";
U.messages=[]
}else{a.util.log(M.logLevel,["SSE reconnect maximum try reached "+k]);
ad(0,"maxReconnectOnClose reached")
}}}}}
}function ah(ay){U.transport="websocket";
if(M.enableProtocol&&ay){var az=a.util.now()-M.ctime;
M.lastTimestamp=Number(M.stime)+Number(az)
}var ax=j(M.url);
if(M.logLevel==="debug"){a.util.debug("Invoking executeWebSocket");
a.util.debug("Using URL: "+ax)
}if(ay&&!M.reconnect){if(X!=null){ak()
}return
}X=i(ax);
if(M.webSocketBinaryType!=null){X.binaryType=M.webSocketBinaryType
}if(M.connectTimeout>0){M.id=setTimeout(function(){if(!ay){var aA={code:1002,reason:"",wasClean:false};
X.onclose(aA);
try{ak()
}catch(aB){}return
}},M.connectTimeout)
}X.onopen=function(aA){w(M);
if(M.logLevel==="debug"){a.util.debug("Websocket successfully opened")
}if(!M.enableProtocol){if(!ay){L("opening","websocket",M)
}else{L("re-opening","websocket",M)
}}ay=true;
X.webSocketOpened=ay;
if(M.method==="POST"){U.state="messageReceived";
X.send(M.data)
}};
X.onmessage=function(aC){w(M);
U.state="messageReceived";
U.status=200;
aC=aC.data;
var aA=typeof(aC)==="string";
if(aA){var aB=v(aC,M,U);
if(!aB){A();
U.responseBody="";
U.messages=[]
}}else{if(!s(M,aC)){return
}U.responseBody=aC;
A();
U.responseBody=null
}};
X.onerror=function(aA){clearTimeout(M.id)
};
X.onclose=function(aA){clearTimeout(M.id);
if(U.state==="closed"){return
}var aB=aA.reason;
if(aB===""){switch(aA.code){case 1000:aB="Normal closure; the connection successfully completed whatever purpose for which it was created.";
break;
case 1001:aB="The endpoint is going away, either because of a server failure or because the browser is navigating away from the page that opened the connection.";
break;
case 1002:aB="The endpoint is terminating the connection due to a protocol error.";
break;
case 1003:aB="The connection is being terminated because the endpoint received data of a type it cannot accept (for example, a text-only endpoint received binary data).";
break;
case 1004:aB="The endpoint is terminating the connection because a data frame was received that is too large.";
break;
case 1005:aB="Unknown: no status code was provided even though one was expected.";
break;
case 1006:aB="Connection was closed abnormally (that is, with no close frame being sent).";
break
}}a.util.warn("Websocket closed, reason: "+aB);
a.util.warn("Websocket closed, wasClean: "+aA.wasClean);
ag(ay);
U.state="closed";
if(au){a.util.log(M.logLevel,["Websocket closed normally"])
}else{if(!ay){O("Websocket failed. Downgrading to Comet and resending")
}else{if(M.reconnect&&U.transport==="websocket"){ak();
if(k++<M.maxReconnectOnClose){L("re-connecting",M.transport,M);
M.id=setTimeout(function(){U.responseBody="";
U.messages=[];
ah(true)
},M.reconnectInterval)
}else{a.util.log(M.logLevel,["Websocket reconnect maximum try reached "+M.requestCount]);
a.util.warn("Websocket error, reason: "+aA.reason);
ad(0,"maxReconnectOnClose reached")
}}}}};
if(X.url===undefined){X.onclose({reason:"Android 4.1 does not support websockets.",wasClean:false})
}}function s(aA,az){var ax=true;
if(a.util.trim(az).length!==0&&aA.enableProtocol&&aA.firstMessage){aA.firstMessage=false;
var ay=az.split(aA.messageDelimiter);
var aB=ay.length===2?0:1;
aA.uuid=a.util.trim(ay[aB]);
aA.stime=a.util.trim(ay[aB+1]);
ax=false;
if(aA.transport!=="long-polling"){am(aA)
}}else{am(aA)
}return ax
}function w(ax){clearTimeout(ax.id);
if(ax.timeout>0&&ax.transport!=="polling"){ax.id=setTimeout(function(){ag(true);
ak();
B()
},ax.timeout)
}}function ad(ax,ay){ak();
clearTimeout(M.id);
U.state="error";
U.reasonPhrase=ay;
U.responseBody="";
U.status=ax;
U.messages=[];
A()
}function v(aB,aA,ax){if(!s(M,aB)){return true
}if(aB.length===0){return true
}if(aA.trackMessageLength){aB=ax.partialMessage+aB.replace(/(\r\n|\n|\r)/gm,"").replace(/^\s+|\s+$/g,"");
var az=[];
var ay=aB.indexOf(aA.messageDelimiter);
while(ay!==-1){var aD=a.util.trim(aB.substring(0,ay));
var aC=+aD;
if(isNaN(aC)){throw new Error('message length "'+aD+'" is not a number')
}ay+=aA.messageDelimiter.length;
if(ay+aC>aB.length){ay=-1
}else{az.push(aB.substring(ay,ay+aC));
aB=aB.substring(ay+aC,aB.length);
ay=aB.indexOf(aA.messageDelimiter)
}}ax.partialMessage=aB;
if(az.length!==0){ax.responseBody=az.join(aA.messageDelimiter);
ax.messages=az;
return false
}else{ax.responseBody="";
ax.messages=[];
return true
}}else{ax.responseBody=aB
}return false
}function O(ax){a.util.log(M.logLevel,[ax]);
if(typeof(M.onTransportFailure)!=="undefined"){M.onTransportFailure(ax,M)
}else{if(typeof(a.util.onTransportFailure)!=="undefined"){a.util.onTransportFailure(ax,M)
}}M.transport=M.fallbackTransport;
var ay=M.connectTimeout===-1?0:M.connectTimeout;
if(M.reconnect&&M.transport!=="none"||M.transport==null){M.method=M.fallbackMethod;
U.transport=M.fallbackTransport;
M.fallbackTransport="none";
M.id=setTimeout(function(){r()
},ay)
}else{ad(500,"Unable to reconnect with fallback transport")
}}function V(az,ax){var ay=M;
if((az!=null)&&(typeof(az)!=="undefined")){ay=az
}if(ax==null){ax=ay.url
}if(!ay.attachHeadersAsQueryString){return ax
}if(ax.indexOf("X-Atmosphere-Framework")!==-1){return ax
}ax+=(ax.indexOf("?")!==-1)?"&":"?";
ax+="X-Atmosphere-tracking-id="+ay.uuid;
ax+="&X-Atmosphere-Framework="+c;
ax+="&X-Atmosphere-Transport="+ay.transport;
if(ay.trackMessageLength){ax+="&X-Atmosphere-TrackMessageSize=true"
}if(ay.lastTimestamp!=null){ax+="&X-Cache-Date="+ay.lastTimestamp
}else{ax+="&X-Cache-Date="+0
}if(ay.contentType!==""){ax+="&Content-Type="+ay.contentType
}if(ay.enableProtocol){ax+="&X-atmo-protocol=true"
}a.util.each(ay.headers,function(aA,aC){var aB=a.util.isFunction(aC)?aC.call(this,ay,az,U):aC;
if(aB!=null){ax+="&"+encodeURIComponent(aA)+"="+encodeURIComponent(aB)
}});
return ax
}function am(ax){if(!ax.isOpen){ax.isOpen=true;
L("opening",ax.transport,ax)
}else{if(ax.isReopen){ax.isReopen=false;
L("re-opening",ax.transport,ax)
}}}function q(az){var ax=M;
if((az!=null)||(typeof(az)!=="undefined")){ax=az
}ax.lastIndex=0;
ax.readyState=0;
if((ax.transport==="jsonp")||((ax.enableXDR)&&(a.util.checkCORSSupport()))){x(ax);
return
}if(a.util.browser.msie&&a.util.browser.version<10){if((ax.transport==="streaming")){if(ax.enableXDR&&window.XDomainRequest){N(ax)
}else{at(ax)
}return
}if((ax.enableXDR)&&(window.XDomainRequest)){N(ax);
return
}}var aA=function(){ax.lastIndex=0;
if(ax.reconnect&&k++<ax.maxReconnectOnClose){L("re-connecting",az.transport,az);
P(ay,ax,az.reconnectInterval)
}else{ad(0,"maxReconnectOnClose reached")
}};
if(ax.force||(ax.reconnect&&(ax.maxRequest===-1||ax.requestCount++<ax.maxRequest))){ax.force=false;
var ay=a.util.xhr();
ay.hasData=false;
g(ay,ax,true);
if(ax.suspend){t=ay
}if(ax.transport!=="polling"){U.transport=ax.transport;
ay.onabort=function(){ag(true)
};
ay.onerror=function(){U.error=true;
try{U.status=XMLHttpRequest.status
}catch(aC){U.status=500
}if(!U.status){U.status=500
}ak();
if(!U.errorHandled){aA()
}}
}ay.onreadystatechange=function(){if(au){return
}U.error=null;
var aD=false;
var aI=false;
if(ax.transport==="streaming"&&ax.readyState>2&&ay.readyState===4){ak();
aA();
return
}ax.readyState=ay.readyState;
if(ax.transport==="streaming"&&ay.readyState>=3){aI=true
}else{if(ax.transport==="long-polling"&&ay.readyState===4){aI=true
}}w(M);
if(ax.transport!=="polling"){if((!ax.enableProtocol||!az.firstMessage)&&ay.readyState===2){am(ax)
}var aC=0;
if(ay.readyState!==0){aC=ay.status>1000?0:ay.status
}if(aC>=300||aC===0){U.errorHandled=true;
ak();
aA();
return
}}if(aI){var aG=ay.responseText;
if(a.util.trim(aG).length===0&&ax.transport==="long-polling"){if(!ay.hasData){aA()
}else{ay.hasData=false
}return
}ay.hasData=true;
ae(ay,M);
if(ax.transport==="streaming"){if(!a.util.browser.opera){var aF=aG.substring(ax.lastIndex,aG.length);
aD=v(aF,ax,U);
ax.lastIndex=aG.length;
if(aD){return
}}else{a.util.iterate(function(){if(U.status!==500&&ay.responseText.length>ax.lastIndex){try{U.status=ay.status;
U.headers=a.util.parseHeaders(ay.getAllResponseHeaders());
ae(ay,M)
}catch(aK){U.status=404
}w(M);
U.state="messageReceived";
var aJ=ay.responseText.substring(ax.lastIndex);
ax.lastIndex=ay.responseText.length;
aD=v(aJ,ax,U);
if(!aD){A()
}J(ay,ax)
}else{if(U.status>400){ax.lastIndex=ay.responseText.length;
return false
}}},0)
}}else{aD=v(aG,ax,U)
}try{U.status=ay.status;
U.headers=a.util.parseHeaders(ay.getAllResponseHeaders());
ae(ay,ax)
}catch(aH){U.status=404
}if(ax.suspend){U.state=U.status===0?"closed":"messageReceived"
}else{U.state="messagePublished"
}var aE=az.transport!=="streaming";
if(aE&&!ax.executeCallbackBeforeReconnect){P(ay,ax,0)
}if(U.responseBody.length!==0&&!aD){A()
}if(aE&&ax.executeCallbackBeforeReconnect){P(ay,ax,0)
}J(ay,ax)
}};
try{ay.send(ax.data);
ai=true
}catch(aB){a.util.log(ax.logLevel,["Unable to connect to "+ax.url])
}}else{if(ax.logLevel==="debug"){a.util.log(ax.logLevel,["Max re-connection reached."])
}ad(0,"maxRequest reached")
}}function g(az,aA,ay){var ax=aA.url;
if(aA.dispatchUrl!=null&&aA.method==="POST"){ax+=aA.dispatchUrl
}ax=V(aA,ax);
ax=a.util.prepareURL(ax);
if(ay){az.open(aA.method,ax,aA.async);
if(aA.connectTimeout>0){aA.id=setTimeout(function(){if(aA.requestCount===0){ak();
D("Connect timeout","closed",200,aA.transport)
}},aA.connectTimeout)
}}if(M.withCredentials){if("withCredentials" in az){az.withCredentials=true
}}if(!M.dropAtmosphereHeaders){az.setRequestHeader("X-Atmosphere-Framework",a.util.version);
az.setRequestHeader("X-Atmosphere-Transport",aA.transport);
if(aA.lastTimestamp!=null){az.setRequestHeader("X-Cache-Date",aA.lastTimestamp)
}else{az.setRequestHeader("X-Cache-Date",0)
}if(aA.trackMessageLength){az.setRequestHeader("X-Atmosphere-TrackMessageSize","true")
}az.setRequestHeader("X-Atmosphere-tracking-id",aA.uuid)
}if(aA.contentType!==""){az.setRequestHeader("Content-Type",aA.contentType)
}a.util.each(aA.headers,function(aB,aD){var aC=a.util.isFunction(aD)?aD.call(this,az,aA,ay,U):aD;
if(aC!=null){az.setRequestHeader(aB,aC)
}})
}function P(ay,az,aA){if(az.reconnect||(az.suspend&&ai)){var ax=0;
if(ay&&ay.readyState!==0){ax=ay.status>1000?0:ay.status
}U.status=ax===0?204:ax;
U.reason=ax===0?"Server resumed the connection or down.":"OK";
clearTimeout(az.id);
az.id=setTimeout(function(){q(az)
},aA)
}}function ac(ax){ax.state="re-connecting";
Z(ax)
}function N(ax){if(ax.transport!=="polling"){C=T(ax);
C.open()
}else{T(ax).open()
}}function T(az){var ay=M;
if((az!=null)&&(typeof(az)!=="undefined")){ay=az
}var aE=ay.transport;
var aD=0;
var ax=new window.XDomainRequest();
var aB=function(){if(ay.transport==="long-polling"&&(ay.reconnect&&(ay.maxRequest===-1||ay.requestCount++<ay.maxRequest))){ax.status=200;
N(ay)
}};
var aC=ay.rewriteURL||function(aG){var aF=/(?:^|;\s*)(JSESSIONID|PHPSESSID)=([^;]*)/.exec(document.cookie);
switch(aF&&aF[1]){case"JSESSIONID":return aG.replace(/;jsessionid=[^\?]*|(\?)|$/,";jsessionid="+aF[2]+"$1");
case"PHPSESSID":return aG.replace(/\?PHPSESSID=[^&]*&?|\?|$/,"?PHPSESSID="+aF[2]+"&").replace(/&$/,"")
}return aG
};
ax.onprogress=function(){aA(ax)
};
ax.onerror=function(){if(ay.transport!=="polling"){ak();
if(k++<ay.maxReconnectOnClose){ay.id=setTimeout(function(){L("re-connecting",az.transport,az);
N(ay)
},ay.reconnectInterval)
}else{ad(0,"maxReconnectOnClose reached")
}}};
ax.onload=function(){};
var aA=function(aF){clearTimeout(ay.id);
var aH=aF.responseText;
aH=aH.substring(aD);
aD+=aH.length;
if(aE!=="polling"){w(ay);
var aG=v(aH,ay,U);
if(aE==="long-polling"&&a.util.trim(aH)===0){return
}if(ay.executeCallbackBeforeReconnect){aB()
}if(!aG){D(U.responseBody,"messageReceived",200,aE)
}if(!ay.executeCallbackBeforeReconnect){aB()
}}};
return{open:function(){var aF=ay.url;
if(ay.dispatchUrl!=null){aF+=ay.dispatchUrl
}aF=V(ay,aF);
ax.open(ay.method,aC(aF));
if(ay.method==="GET"){ax.send()
}else{ax.send(ay.data)
}if(ay.connectTimeout>0){ay.id=setTimeout(function(){if(ay.requestCount===0){ak();
D("Connect timeout","closed",200,ay.transport)
}},ay.connectTimeout)
}},close:function(){ax.abort()
}}
}function at(ax){C=u(ax);
C.open()
}function u(aA){var az=M;
if((aA!=null)&&(typeof(aA)!=="undefined")){az=aA
}var ay;
var aB=new window.ActiveXObject("htmlfile");
aB.open();
aB.close();
var ax=az.url;
if(az.dispatchUrl!=null){ax+=az.dispatchUrl
}if(az.transport!=="polling"){U.transport=az.transport
}return{open:function(){var aC=aB.createElement("iframe");
ax=V(az);
if(az.data!==""){ax+="&X-Atmosphere-Post-Body="+encodeURIComponent(az.data)
}ax=a.util.prepareURL(ax);
aC.src=ax;
aB.body.appendChild(aC);
var aD=aC.contentDocument||aC.contentWindow.document;
ay=a.util.iterate(function(){try{if(!aD.firstChild){return
}var aG=aD.body?aD.body.lastChild:aD;
var aI=function(){var aK=aG.cloneNode(true);
aK.appendChild(aD.createTextNode("."));
var aJ=aK.innerText;
aJ=aJ.substring(0,aJ.length-1);
return aJ
};
if(!aD.body||!aD.body.firstChild||aD.body.firstChild.nodeName.toLowerCase()!=="pre"){var aF=aD.head||aD.getElementsByTagName("head")[0]||aD.documentElement||aD;
var aE=aD.createElement("script");
aE.text="document.write('<plaintext>')";
aF.insertBefore(aE,aF.firstChild);
aF.removeChild(aE);
aG=aD.body.lastChild
}if(az.closed){az.isReopen=true
}ay=a.util.iterate(function(){var aK=aI();
if(aK.length>az.lastIndex){w(M);
U.status=200;
U.error=null;
aG.innerText="";
var aJ=v(aK,az,U);
if(aJ){return""
}D(U.responseBody,"messageReceived",200,az.transport)
}az.lastIndex=0;
if(aD.readyState==="complete"){ag(true);
L("re-connecting",az.transport,az);
az.id=setTimeout(function(){at(az)
},az.reconnectInterval);
return false
}},null);
return false
}catch(aH){U.error=true;
L("re-connecting",az.transport,az);
if(k++<az.maxReconnectOnClose){az.id=setTimeout(function(){at(az)
},az.reconnectInterval)
}else{ad(0,"maxReconnectOnClose reached")
}aB.execCommand("Stop");
aB.close();
return false
}})
},close:function(){if(ay){ay()
}aB.execCommand("Stop");
ag(true)
}}
}function aj(ax){if(p!=null){l(ax)
}else{if(t!=null||n!=null){h(ax)
}else{if(C!=null){W(ax)
}else{if(E!=null){S(ax)
}else{if(X!=null){F(ax)
}}}}}}function m(ay,ax){if(!ax){ax=an(ay)
}ax.transport="polling";
ax.method="GET";
ax.async=false;
ax.reconnect=false;
ax.force=true;
ax.suspend=false;
q(ax)
}function l(ax){p.send(ax)
}function z(ay){if(ay.length===0){return
}try{if(p){p.localSend(ay)
}else{if(ao){ao.signal("localMessage",a.util.stringifyJSON({id:H,event:ay}))
}}}catch(ax){a.util.error(ax)
}}function h(ay){var ax=an(ay);
q(ax)
}function W(ay){if(M.enableXDR&&a.util.checkCORSSupport()){var ax=an(ay);
ax.reconnect=false;
x(ax)
}else{h(ay)
}}function S(ax){h(ax)
}function R(ax){var ay=ax;
if(typeof(ay)==="object"){ay=ax.data
}return ay
}function an(ay){var az=R(ay);
var ax={connected:false,timeout:60000,method:"POST",url:M.url,contentType:M.contentType,headers:M.headers,reconnect:true,callback:null,data:az,suspend:false,maxRequest:-1,logLevel:"info",requestCount:0,withCredentials:M.withCredentials,transport:"polling",isOpen:true,attachHeadersAsQueryString:true,enableXDR:M.enableXDR,uuid:M.uuid,dispatchUrl:M.dispatchUrl,enableProtocol:false,messageDelimiter:"|",maxReconnectOnClose:M.maxReconnectOnClose};
if(typeof(ay)==="object"){ax=a.util.extend(ax,ay)
}return ax
}function F(ax){var aA=R(ax);
var ay;
try{if(M.dispatchUrl!=null){ay=M.webSocketPathDelimiter+M.dispatchUrl+M.webSocketPathDelimiter+aA
}else{ay=aA
}X.send(ay)
}catch(az){X.onclose=function(aB){};
ak();
O("Websocket failed. Downgrading to Comet and resending "+ay);
h(ax)
}}function aa(ay){var ax=a.util.parseJSON(ay);
if(ax.id!==H){if(typeof(M.onLocalMessage)!=="undefined"){M.onLocalMessage(ax.event)
}else{if(typeof(a.util.onLocalMessage)!=="undefined"){a.util.onLocalMessage(ax.event)
}}}}function D(aA,ax,ay,az){U.responseBody=aA;
U.transport=az;
U.status=ay;
U.state=ax;
A()
}function ae(ax,aA){if(!aA.readResponsesHeaders&&!aA.enableProtocol){aA.lastTimestamp=a.util.now();
aA.uuid=H;
return
}try{var az=ax.getResponseHeader("X-Cache-Date");
if(az&&az!=null&&az.length>0){aA.lastTimestamp=az.split(" ").pop()
}var ay=ax.getResponseHeader("X-Atmosphere-tracking-id");
if(ay&&ay!=null){aA.uuid=ay.split(" ").pop()
}if(aA.headers){a.util.each(M.headers,function(aD){var aC=ax.getResponseHeader(aD);
if(aC){U.headers[aD]=aC
}})
}}catch(aB){}}function Z(ax){ar(ax,M);
ar(ax,a.util)
}function ar(ay,az){switch(ay.state){case"messageReceived":k=0;
if(typeof(az.onMessage)!=="undefined"){az.onMessage(ay)
}break;
case"error":if(typeof(az.onError)!=="undefined"){az.onError(ay)
}break;
case"opening":if(typeof(az.onOpen)!=="undefined"){az.onOpen(ay)
}break;
case"messagePublished":if(typeof(az.onMessagePublished)!=="undefined"){az.onMessagePublished(ay)
}break;
case"re-connecting":if(typeof(az.onReconnect)!=="undefined"){az.onReconnect(M,ay)
}break;
case"re-opening":if(typeof(az.onReopen)!=="undefined"){az.onReopen(M,ay)
}break;
case"fail-to-reconnect":if(typeof(az.onFailureToReconnect)!=="undefined"){az.onFailureToReconnect(M,ay)
}break;
case"unsubscribe":case"closed":var ax=typeof(M.closed)!=="undefined"?M.closed:false;
if(typeof(az.onClose)!=="undefined"&&!ax){az.onClose(ay)
}M.closed=true;
break
}}function ag(ax){if(U.state!=="closed"){U.state="closed";
U.responseBody="";
U.messages=[];
U.status=!ax?501:200;
A()
}}function A(){var az=function(aC,aD){aD(U)
};
if(p==null&&Y!=null){Y(U.responseBody)
}M.reconnect=M.mrequest;
var ax=typeof(U.responseBody)==="string";
var aA=(ax&&M.trackMessageLength)?(U.messages.length>0?U.messages:[""]):new Array(U.responseBody);
for(var ay=0;
ay<aA.length;
ay++){if(aA.length>1&&aA[ay].length===0){continue
}U.responseBody=(ax)?a.util.trim(aA[ay]):aA[ay];
if(p==null&&Y!=null){Y(U.responseBody)
}if(U.responseBody.length===0&&U.state==="messageReceived"){continue
}Z(U);
if(e.length>0){if(M.logLevel==="debug"){a.util.debug("Invoking "+e.length+" global callbacks: "+U.state)
}try{a.util.each(e,az)
}catch(aB){a.util.log(M.logLevel,["Callback exception"+aB])
}}if(typeof(M.callback)==="function"){if(M.logLevel==="debug"){a.util.debug("Invoking request callbacks")
}try{M.callback(U)
}catch(aB){a.util.log(M.logLevel,["Callback exception"+aB])
}}}}this.subscribe=function(ax){aw(ax);
r()
};
this.execute=function(){r()
};
this.close=function(){al()
};
this.disconnect=function(){B()
};
this.getUrl=function(){return M.url
};
this.push=function(az,ay){if(ay!=null){var ax=M.dispatchUrl;
M.dispatchUrl=ay;
aj(az);
M.dispatchUrl=ax
}else{aj(az)
}};
this.getUUID=function(){return M.uuid
};
this.pushLocal=function(ax){z(ax)
};
this.enableProtocol=function(ax){return M.enableProtocol
};
this.request=M;
this.response=U
}};
a.subscribe=function(g,j,i){if(typeof(j)==="function"){a.addCallback(j)
}if(typeof(g)!=="string"){i=g
}else{i.url=g
}var h=new a.AtmosphereRequest(i);
h.execute();
f[f.length]=h;
return h
};
a.unsubscribe=function(){if(f.length>0){var g=[].concat(f);
for(var j=0;
j<g.length;
j++){var h=g[j];
h.close();
clearTimeout(h.response.request.id)
}}f=[];
e=[]
};
a.unsubscribeUrl=function(h){var g=-1;
if(f.length>0){for(var k=0;
k<f.length;
k++){var j=f[k];
if(j.getUrl()===h){j.close();
clearTimeout(j.response.request.id);
g=k;
break
}}}if(g>=0){f.splice(g,1)
}};
a.addCallback=function(g){if(a.util.inArray(g,e)===-1){e.push(g)
}};
a.removeCallback=function(h){var g=a.util.inArray(h,e);
if(g!==-1){e.splice(g,1)
}};
a.util={browser:{},parseHeaders:function(h){var g,j=/^(.*?):[ \t]*([^\r\n]*)\r?$/mg,i={};
while(g=j.exec(h)){i[g[1]]=g[2]
}return i
},now:function(){return new Date().getTime()
},isArray:function(g){return Object.prototype.toString.call(g)==="[object Array]"
},isBinary:function(h){var g=Object.prototype.toString.call(h);
return g==="[object Blob]"||g==="[object ArrayBuffer]"
},isFunction:function(g){return Object.prototype.toString.call(g)==="[object Function]"
},getAbsoluteURL:function(g){var h=document.createElement("div");
h.innerHTML='<a href="'+g+'"/>';
return encodeURI(decodeURI(h.firstChild.href))
},prepareURL:function(h){var i=a.util.now();
var g=h.replace(/([?&])_=[^&]*/,"$1_="+i);
return g+(g===h?(/\?/.test(h)?"&":"?")+"_="+i:"")
},trim:function(g){if(!String.prototype.trim){return g.toString().replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"").replace(/\s+/g," ")
}else{return g.toString().trim()
}},param:function(k){var i,g=[];
function j(l,m){m=a.util.isFunction(m)?m():(m==null?"":m);
g.push(encodeURIComponent(l)+"="+encodeURIComponent(m))
}function h(m,n){var l;
if(a.util.isArray(n)){a.util.each(n,function(p,o){if(/\[\]$/.test(m)){j(m,o)
}else{h(m+"["+(typeof o==="object"?p:"")+"]",o)
}})
}else{if(Object.prototype.toString.call(n)==="[object Object]"){for(l in n){h(m+"["+l+"]",n[l])
}}else{j(m,n)
}}}for(i in k){h(i,k[i])
}return g.join("&").replace(/%20/g,"+")
},supportStorage:function(){var h=window.localStorage;
if(h){try{h.setItem("t","t");
h.removeItem("t");
return window.StorageEvent&&!a.util.browser.msie&&!(a.util.browser.mozilla&&a.util.browser.version.split(".")[0]==="1")
}catch(g){}}return false
},iterate:function(i,h){var j;
h=h||0;
(function g(){j=setTimeout(function(){if(i()===false){return
}g()
},h)
})();
return function(){clearTimeout(j)
}
},each:function(m,n,h){var l,j=0,k=m.length,g=a.util.isArray(m);
if(h){if(g){for(;
j<k;
j++){l=n.apply(m[j],h);
if(l===false){break
}}}else{for(j in m){l=n.apply(m[j],h);
if(l===false){break
}}}}else{if(g){for(;
j<k;
j++){l=n.call(m[j],j,m[j]);
if(l===false){break
}}}else{for(j in m){l=n.call(m[j],j,m[j]);
if(l===false){break
}}}}return m
},extend:function(k){var j,h,g;
for(j=1;
j<arguments.length;
j++){if((h=arguments[j])!=null){for(g in h){k[g]=h[g]
}}}return k
},on:function(i,h,g){if(i.addEventListener){i.addEventListener(h,g,false)
}else{if(i.attachEvent){i.attachEvent("on"+h,g)
}}},off:function(i,h,g){if(i.removeEventListener){i.removeEventListener(h,g,false)
}else{if(i.detachEvent){i.detachEvent("on"+h,g)
}}},log:function(i,h){if(window.console){var g=window.console[i];
if(typeof g==="function"){g.apply(window.console,h)
}}},warn:function(){a.util.log("warn",arguments)
},info:function(){a.util.log("info",arguments)
},debug:function(){a.util.log("debug",arguments)
},error:function(){a.util.log("error",arguments)
},xhr:function(){try{return new window.XMLHttpRequest()
}catch(h){try{return new window.ActiveXObject("Microsoft.XMLHTTP")
}catch(g){}}},parseJSON:function(g){return !g?null:window.JSON&&window.JSON.parse?window.JSON.parse(g):new Function("return "+g)()
},stringifyJSON:function(i){var l=/[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,j={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};
function g(m){return'"'+m.replace(l,function(n){var o=j[n];
return typeof o==="string"?o:"\\u"+("0000"+n.charCodeAt(0).toString(16)).slice(-4)
})+'"'
}function h(m){return m<10?"0"+m:m
}return window.JSON&&window.JSON.stringify?window.JSON.stringify(i):(function k(r,q){var p,o,m,n,t=q[r],s=typeof t;
if(t&&typeof t==="object"&&typeof t.toJSON==="function"){t=t.toJSON(r);
s=typeof t
}switch(s){case"string":return g(t);
case"number":return isFinite(t)?String(t):"null";
case"boolean":return String(t);
case"object":if(!t){return"null"
}switch(Object.prototype.toString.call(t)){case"[object Date]":return isFinite(t.valueOf())?'"'+t.getUTCFullYear()+"-"+h(t.getUTCMonth()+1)+"-"+h(t.getUTCDate())+"T"+h(t.getUTCHours())+":"+h(t.getUTCMinutes())+":"+h(t.getUTCSeconds())+'Z"':"null";
case"[object Array]":m=t.length;
n=[];
for(p=0;
p<m;
p++){n.push(k(p,t)||"null")
}return"["+n.join(",")+"]";
default:n=[];
for(p in t){if(b.call(t,p)){o=k(p,t);
if(o){n.push(g(p)+":"+o)
}}}return"{"+n.join(",")+"}"
}}})("",{"":i})
},checkCORSSupport:function(){if(a.util.browser.msie&&!window.XDomainRequest){return true
}else{if(a.util.browser.opera&&a.util.browser.version<12){return true
}}var g=navigator.userAgent.toLowerCase();
var h=g.indexOf("android")>-1;
if(h){return true
}return false
}};
d=a.util.now();
(function(){var h=navigator.userAgent.toLowerCase(),g=/(chrome)[ \/]([\w.]+)/.exec(h)||/(webkit)[ \/]([\w.]+)/.exec(h)||/(opera)(?:.*version|)[ \/]([\w.]+)/.exec(h)||/(msie) ([\w.]+)/.exec(h)||h.indexOf("compatible")<0&&/(mozilla)(?:.*? rv:([\w.]+)|)/.exec(h)||[];
a.util.browser[g[1]||""]=true;
a.util.browser.version=g[2]||"0";
if(a.util.browser.msie||(a.util.browser.mozilla&&a.util.browser.version.split(".")[0]==="1")){a.util.storage=false
}})();
a.util.on(window,"unload",function(g){a.unsubscribe()
});
a.util.on(window,"keypress",function(g){if(g.which===27){g.preventDefault()
}});
a.util.on(window,"offline",function(){a.unsubscribe()
});
window.atmosphere=a
})();
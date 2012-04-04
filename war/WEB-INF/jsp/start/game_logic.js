var colors = ['white', 'black', 'green', 'blue', 'red', 'magenta', 'yellow', 'white'];

function addHandler(object, event, handler)
{
  if (typeof object.addEventListener != 'undefined')
    object.addEventListener(event, handler, false);
  else if (typeof object.attachEvent != 'undefined')
    object.attachEvent('on' + event, handler);
  else
    throw "Incompatible browser";
}

function removeHandler(object, event, handler)
{
  if (typeof object.removeEventListener != 'undefined')
    object.removeEventListener(event, handler, false);
  else if (typeof object.detachEvent != 'undefined')
    object.detachEvent('on' + event, handler);
  else
    throw "Incompatible browser";
}

function getBounds(element)
{
  var left = element.offsetLeft;
  var top = element.offsetTop;
  for (var parent = element.offsetParent; parent; parent = parent.offsetParent)
  {
    left += parent.offsetLeft - parent.scrollLeft;
    top += parent.offsetTop - parent.scrollTop
  }
  return {left: left, top: top, width: element.offsetWidth, height: element.offsetHeight};
}

function wrapper(handler, func) 
{
	var args = ([].slice.call(arguments)).slice(2);
	return function() {
		handler(func, args);
	}
}

function createRequest(url, handler, args)
{
	this.req = null;
	this.url = url;
	this.handler = handler;

	this.loadJSON = function() {
		this.req = null;
		if (window.XMLHttpRequest) {
			try {
				this.req = new XMLHttpRequest();
			} catch (e){}
		} else if (window.ActiveXObject) {
			try {
				this.req = new ActiveXObject('Msxml2.XMLHTTP');
			} catch (e){
				try {
					this.req = new ActiveXObject('Microsoft.XMLHTTP');
				} catch (e){}
			}
		}
	    if (this.req) {       
        this.req.open("GET", this.url , true);
        this.req.onreadystatechange = wrapper(this.handler, this.processReqChange, this.req);
        this.req.send(null);
		}
	}

	this.processReqChange = function(req) {
		try { // Важно!
			// только при состоянии "complete"
			if (req.readyState == 4) {
			//alert(req.status);
				if (req.status == 200) {
					return JSON.parse(req.responseText);
				} else {
					alert("Не удалось получить данные:\n" +
						req.statusText);
				}
			}
		}
		catch( e ) {
			// alert('Ошибка: ' + e.description);
			// В связи с багом XMLHttpRequest в Firefox приходится отлавливать ошибку
			// Bugzilla Bug 238559 XMLHttpRequest needs a way to report networking errors
			// https://bugzilla.mozilla.org/show_bug.cgi?id=238559
		}
  
		return -1;
	}
}

function getBinNumber(xLen, yLen)
{
	var x = Math.floor(xLen / 54.5);
	var y = Math.floor(yLen / 54.5);
	return [ x, y, y * 10 + x ];
}

function onTableClick(event)
{
	event = event || window.event;
	//table.style.backgroundColor = colors[Math.floor(Math.random() * 40)%7];
	//var bounds = getBounds(table);
	//var diffX = Math.abs(event.pageX - target.ball.offsetLeft - 30);
	//var diffY = Math.abs(event.pageY - target.ball.offsetTop - 25);
	//alert('[' + diffX + ', ' + diffY + ']');
	//if (diffX < 50 && diffY < 45)
	var res = getBinNumber(event.pageX - table.offsetLeft, 
						 event.pageY - table.offsetTop);

	if (binSet[res[1]][res[0]] != 0) {
		return;
	}
	
	var n = res[2];
	var coord = { 'number' : n, 'region' : 0 };
	var req = new createRequest('http://localhost:8080/tmp/target.htm?coord=' + JSON.stringify(coord),
				function(func, req) {
					//alert(req);
					var token = func.apply(this, req);
					
					if (token != -1) {
						setbinBackgroundImage(n, token.type);
						binSet[res[1]][res[0]] = -1;
					}
				});
	req.loadJSON();
	//alert('[ ' + x + ', ' + y + ', ' + n + ' ]');
}

function onBallClick(event)
{
	event = event || window.event;
	var res = getBinNumber(event.pageX - table.offsetLeft, 
						 event.pageY - table.offsetTop);
	//alert(n);
	if (target.going == true) {
		//var coords = { 'x' : event.pageX, 'y' : event.pageY };
		//loadJSON('http://localhost/springapp/target.htm?=coords=' + coords.toJSONString());
	}
	
	binSet[res[1]][res[0]]++;
	//var bounds = getBounds(table);
	//alert('Координаты элемента: ' +
    //  '(' + bounds.left + ',' + bounds.top + ') x ' +
    //  '(' + bounds.width + ',' + bounds.height + ')');
	//alert('[' + event.pageX + ', ' + event.pageY + ']');
}

function ballStart()
{
	target.going = true;
	setTarget(0);
	start.innerHTML = 'Continue';
}

function ballStop()
{
	target.going = false;
	start.innerHTML = 'Resume';
}

function setTarget(i) 
{
	if (!(i % gridStep)) {
		if (oneDirect > 0)
			target.setRandDirection();
		oneDirect--;
	}
		
	if (!target.isCond() || target.isBinSet())
		target.setOppositeDirection();
				
	target.toDirection();
	
	if (target.going)
		setTimeout('setTarget(' + (i + 1) + ')', 10);
}

function init() 
{
	minLeft = 448.5;
	maxLeft = 937.5;
	maxTop  = -54.5;
	minTop  = -540;
	gridStep = 54.5;
	oneDirect = 2;
	table = document.getElementById('table');
	start = document.getElementById('start');
	result = document.getElementById('result');
	remoteUrl = 'http://old.parkaventura.ru/tmp/start/';
	typeToImage = {
		0 : '1.png',
		1 : 'burn.png',
		2 : 'dukeWaveRed.gif',
		3 : 'images.jpg',
		4 : 'lamp.png',
		5 :  'toy.png',
		6 : 'toy-2 copy.png'
	};
	binSet = [
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
		[ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]
	];
	target = {
		top    : -164.5,
		left   : 559.3,
		direction : 0,
		name : "Ball",
		going : true,
		ball   : document.getElementById('target'),
		getName : function()
		{
			return this.name;
		},
		toUp : function ()
		{
			this.top--;
			this.ball.style.marginTop = this.top + 'px';
		},
		toDown : function ()
		{
			this.top++;
			this.ball.style.marginTop = this.top + 'px';
		},
		toLeft : function ()
		{
			this.left--;
			this.ball.style.marginLeft = this.left + 'px';
		},
		toRight : function ()
		{
			this.left++;
			this.ball.style.marginLeft = this.left + 'px';
		},
		toDirection : function()
		{
			switch(this.direction) {
				case 0:
					this.toUp();
					break;
				case 1:
					this.toLeft();
					break;
				case 2:
					this.toDown();
					break;
				case 3:
					this.toRight();
					break;
			}
		},
		setDirection : function(i) 
		{
			this.direction = Math.abs(Math.floor(i)%4);
		},
		setRandDirection : function() 
		{
			this.direction = Math.floor(Math.random() * 4.0);
			oneDirect = 2;
		},
		setOppositeDirection : function()
		{
			this.direction = (this.direction + 2) % 4;
			this.toDirection();
			this.toDirection();
			this.toDirection();
			this.toDirection();
			this.toDirection();
		},
		isCond : function()
		{
			if (this.left >= minLeft && this.left <= maxLeft &&
				this.top  >= minTop  && this.top  <= maxTop) {
				return true;
			}
			
			return false;
		},
		isBinSet : function()
		{
			var res = getBinNumber(this.left - 450, (545 + this.top));
			//alert('[ ' + this.left + ', ' + (table.offsetTop + this.top) + " ]\n" +
			//	  '[ ' + res[1] + ', ' + res[0] + ' ]');
			if (binSet[res[1]][res[0]] < 0) {
				return true;
			}
			
			return false;
		}
	};

	target.toUp();
	target.toLeft();
	setTarget(0);
	addHandler(table, 'click', onTableClick);
	addHandler(target.ball, 'click', onBallClick);
	table.style.backgroundColor = colors[1];
}

function setbinBackgroundImage(id, type) 
{
	document.getElementById('bin-' + id).src = remoteUrl + typeToImage[type%7];
}

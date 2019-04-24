// Page.js
function Page(divid, objid){
	this.divid = divid;
	this.objid = objid;
	this.clickevent;
	this.start = 0;
	this.pagenum = 0;
	this.totalnum = 0;
	this.currpage = 0;
	this.activeliid;
	this.pagesplit = 5;
	this.currsplitpage = 1;
	this.pagesplittotal = 1;
	
	if(Page.prototype.init == undefined){
		Page.prototype.init = function(start,pagenum,totalnum,clickevent){
			this.start = start;
			this.pagenum = pagenum;
			this.totalnum = totalnum;
			this.clickevent = clickevent;
			this.currpage = parseInt((this.start-1)/this.pagenum*1+1); // 当前页数
			this.setdivvalue(this.currsplitpage); // 设置div
		}
	}

	if(Page.prototype.setdivvalue == undefined){
		Page.prototype.setdivvalue = function(num){
			var v_xhcount = num*this.pagesplit;
			num = (num-1)*this.pagesplit+1;
			var v_totalpagecount = Math.ceil(this.totalnum/this.pagenum); // 总页数
			this.pagesplittotal = Math.ceil(v_totalpagecount/this.pagesplit); // 可以切割成几个滚动
			if(v_xhcount>v_totalpagecount){
				v_xhcount = v_totalpagecount;
			}
			var v_pagestr = "";
			for(var i=num;i<=v_xhcount;i++){
				if(i==this.currpage){
					v_pagestr += "<li id=\""+this.divid+"_page"+i+"\" class=\"active\"><a href=\"#\"  onclick=\""+this.objid+".liclick('"+this.divid+"_page"+i+"','"+i+"');"+this.clickevent+"("+i+");\">"+i+"</a></li>";
					this.activeliid = this.divid+"_page"+i;
				}else{
					v_pagestr += "<li id=\""+this.divid+"_page"+i+"\" ><a href=\"#\" onclick=\""+this.objid+".liclick('"+this.divid+"_page"+i+"','"+i+"');"+this.clickevent+"("+i+");\">"+i+"</a></li>";
				}
			}
			$("#"+this.divid+"").html("<ul class=\"pagination\"><li><a href=\"#\" onclick=\""+this.objid+".previous();\">&laquo;</a></li>"+
			v_pagestr+
			"<li><a href=\"#\" onclick=\""+this.objid+".next();\">&raquo;</a></li></ul>");
			//alert(v_pagestr);
		}
	}

	if(Page.prototype.liclick == undefined){
		Page.prototype.liclick = function(liid, i){
			$("#"+this.activeliid+"").attr("class", "");
			$("#"+liid+"").attr("class", "active");
			this.activeliid = liid;
			this.currpage = i;
		}
	}

	if(Page.prototype.previous == undefined){
		Page.prototype.previous = function(){
			if(this.pagesplittotal>1 && this.currsplitpage>1){
				this.currsplitpage = this.currsplitpage - 1;
				this.setdivvalue(this.currsplitpage); // 设置div
			}
		}
	}

	if(Page.prototype.next == undefined){
		Page.prototype.next = function(){
			if(this.pagesplittotal>this.currsplitpage){
				this.currsplitpage = this.currsplitpage + 1;
				this.setdivvalue(this.currsplitpage); // 设置div
			}
		}
	}
}


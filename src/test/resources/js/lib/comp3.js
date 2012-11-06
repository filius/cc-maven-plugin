goog.provide("com.bs.Comp3")
goog.require("com.bs.Comp2")

com.bs.Comp3 = function(){
	com.bs.Comp2.call(this);
}
goog.inherits(com.bs.Comp3,com.bs.Comp2);

com.bs.Comp3.prototype.test3 = function(){
	console.log("test3");
}
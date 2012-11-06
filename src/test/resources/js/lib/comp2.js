goog.provide("com.bs.Comp2")
goog.require("com.bs.Comp1")

com.bs.Comp2 = function(){
	com.bs.Comp1.call(this);
}
goog.inherits(com.bs.Comp2,com.bs.Comp1);

com.bs.Comp2.prototype.test2 = function(){
	console.log("test2");
}
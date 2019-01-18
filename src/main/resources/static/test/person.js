(function ($) {
    'use strict';
    var Car = function () {
        this.name = ['Bob', 'Smith'];
        this.age = 32;
        this.gender = 'male';
        this.interests = ['music', 'skiing'];
        this.bio = function () {
            alert(this.name[0] + ' ' + this.name[1] + ' is ' + this.age + ' years old. He likes ' + this.interests[0] + ' and ' + this.interests[1] + '.');
        };
        this.greeting = function () {
            alert('Hi! I\'m ' + this.name[0] + '.');
        }
    };
    //Javascript规定，每一个构造函数都有一个prototype属性，指向另一个对象。这个对象的所有属性和方法，都会被构造函数的实例继承。
    Car.prototype.color = "white";
    Car.prototype.whistle = function(msg){
      console.log("di~ didi~" + msg);
    };

    window.car = new Car();
})(jQuery);
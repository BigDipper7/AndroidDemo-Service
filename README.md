AndroidDemo-Service
===================

自己写的一个感觉很好的service的demo，启动挂载和结束方法用的是startService和stopService的方法，没有用到bind和unbind这个东西。

其实感觉还是比较好用的，上面的整体的额过程是这样子的。

###整体流程

    开始是首先，只要触发按动的那个时间，那么onclick触发，最后肯定是要进去那个service，start多次。
    然后呢，由于是启动多次，那么最后mediaplayer的启动init实在service的oncreate里面实现，那么只要启动了这个service，即便是这个启动用的mainactivity被消掉了，this.finish，但是仍然是要调用startservice方法，再次启动service。
    那么就会出现这种情况，当我即便是有stopservice的方法，比如按动exit的那个button，那么最后也是先在mainactivity里面启动service，但是由于先触发了stopservice，那么首先调用service的ondestroy方法，销毁整个service，然后最后由于又一次的进入了startservice的这个方法，那么再一次将service启动。
    
    而且有个很独特的方法，就是无论是是不是最后this.finish了，都要等着onclick函数走完，然后最后才能够finish当前的这个activity

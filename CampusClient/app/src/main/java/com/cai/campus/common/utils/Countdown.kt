package com.cai.campus.common.utils

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object Countdown {
    fun start(count: Long, next: (Long) -> Unit, down: () -> Unit) {
        Observable.interval(0, 1, TimeUnit.SECONDS) //延迟0，间隔1s，单位秒
            .take(count + 1) //限制发射次数（因为倒计时要显示 3 2 1 0 四个数字）
            //使用map将数字转换，这里aLong是从0开始增长的,所以减去aLong就会输出3 2 1 0这种样式
            .map { aLong -> count - aLong }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Long?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(num: Long) {
                    next(num)
                }
                override fun onError(e: Throwable) {}
                override fun onComplete() {
                    down()
                }
            })
    }
}
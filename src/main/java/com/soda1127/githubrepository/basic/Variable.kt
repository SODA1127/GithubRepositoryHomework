package com.soda1127.githubrepository.basic

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 * Created by leekijung on 2020. 2. 18..
 */

class Variable<T>
@JvmOverloads
constructor(
    @Volatile var value: T? = null,
    private val alwaysClearOnStop: Boolean = false
) : LifecycleObserver {

    private var action: ((T) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null

    private var disposable: Disposable? = null

    private val publishSubject by lazy<PublishSubject<T>> { PublishSubject.create() }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun active() {
        action?.let { action ->
            disposable?.let { if (!it.isDisposed) it.dispose() }
            disposable = asObservable().subscribe({ action.invoke(it) }, { onError?.invoke(it) })
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun cleanUp() {
        if (!alwaysClearOnStop) return
        disposable?.let { if (!it.isDisposed) it.dispose() }
    }

    init {
        value?.let { publishSubject.onNext(it) }
    }

    @Synchronized
    fun get(): T? = value

    @Synchronized
    fun set() {
        try {
            val voidConstructor = Void::class.java.getDeclaredConstructor()
            voidConstructor.isAccessible = true
            val v = voidConstructor.newInstance()
            publishSubject.onNext(v as T)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Synchronized
    infix fun set(value: T) {
        this.value = value
        publishSubject.onNext(value)
    }

    operator fun next() {
        this.value?.let { publishSubject.onNext(it) }
    }

    fun asObservable() = publishSubject.serialize()

    fun Observable<T>.subscribe(lifecycle: Lifecycle? = null, action: (T) -> Unit, onError: ((Throwable) -> Unit)? = null): Disposable? {
        lifecycle?.addObserver(this@Variable)
        disposable = this.subscribe({ action.invoke(it) }, { onError?.invoke(it) })
        this@Variable.action = action
        this@Variable.onError = onError
        return disposable
    }

    fun subscribe(lifecycle: Lifecycle? = null, action: (T) -> Unit, onError: ((Throwable) -> Unit)? = null): Disposable? {
        lifecycle?.addObserver(this@Variable)
        disposable = asObservable().subscribe({ action.invoke(it) }, { onError?.invoke(it) })
        this.action = action
        this.onError = onError
        return disposable
    }
}
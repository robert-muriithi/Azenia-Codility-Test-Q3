interface Repository<T> {
    fun get(): Iterable<T>
}
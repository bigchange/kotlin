// !LANGUAGE: +InlineClasses
// WITH_RUNTIME

inline class MyUInt(val x: Int)

inline class MyUIntArray(private val storage: IntArray) : Collection<MyUInt> {
    public override val size: Int get() = storage.size

    override operator fun iterator() = TODO()
    override fun contains(element: MyUInt): Boolean = storage.contains(element.x)
    override fun containsAll(elements: Collection<MyUInt>): Boolean = elements.all { storage.contains(it.x) }
    override fun isEmpty(): Boolean = TODO()
}

fun box(): String {
    val uints = MyUIntArray(intArrayOf(0, 1, 42))

    if (MyUInt(42) !in uints) return "Fail 1"

    val ints = listOf(MyUInt(1), MyUInt(0))
    if (!uints.containsAll(ints)) return "Fail 2"

    return "OK"
}
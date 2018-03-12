package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) =
            when {
                year != other.year -> year - other.year
                month != other.month -> month - other.month
                else -> dayOfMonth - other.dayOfMonth
            }

    operator fun plus(interval: TimeInterval): MyDate {
        return addTimeIntervals(interval, 1)
    }

    operator fun plus(interval: RepeatedTimeInterval):MyDate {
        return addTimeIntervals(interval.interval, interval.count)
    }
}
operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(count: Int) :RepeatedTimeInterval {
    return RepeatedTimeInterval(this, count)
}

class RepeatedTimeInterval(val interval:TimeInterval, val count : Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate>{

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var next: MyDate = start

            override fun hasNext(): Boolean {
                return contains(next)
            }

            override fun next(): MyDate {
                val current = next
                next = next.nextDay()
                return current

            }

        }
    }

    operator fun contains(d: MyDate): Boolean {
        return start <= d && d <= endInclusive
    }
}
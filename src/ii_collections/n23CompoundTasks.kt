package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
//    todoCollectionTask()
    return customers.filter { product in it.orderedProducts }
            .toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
//    todoCollectionTask()
    return orders.filter { it.isDelivered }
            .map { it.products }
            .flatMap { it.toHashSet() }
            .maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
//    todoCollectionTask()
    return customers.map { it.orders }
            .flatMap { it.toList() }
            .map { it.products }
            .flatMap { it.toList() }
            .filter { product == it  }
            .count()
}

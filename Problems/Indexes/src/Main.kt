fun solution(products: List<String>, product: String) {
    for (i in products.indices) {
        if (products[i] == product) {
            print("$i ")
        }
    }
}
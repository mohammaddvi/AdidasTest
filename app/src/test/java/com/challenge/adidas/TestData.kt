package com.challenge.adidas


val shoesReviews: List<Review> = listOf(
    Review(
        "100",
        "",
        4,
        "",
        "above clouds with this shoes"
    ),
    Review(
        "200",
        "",
        1,
        "",
        "the worst shoes that you can imagine"
    ),
    Review(
        "300",
        "",
        5,
        "",
        "I`m add this review from sky, these are awsome"
    ),
    Review(
        "400",
        "",
        1,
        "",
        "what the hell, I am close to core of earth "
    )
)

val shirtReviews: List<Review> = listOf(
    Review(
        "100",
        "",
        4,
        "",
        "so funny you can`t imagine how fun is that"
    ),
    Review(
        "200",
        "",
        1,
        "",
        "this shirt doesn`t work becarefull :|"
    ),
    Review(
        "300",
        "",
        5,
        "",
        "oh that`s incredible we have so much fun time with my friends and this shirt"
    ),
    Review(
        "400",
        "",
        1,
        "",
        "you should buy pants and socks with this shirt because there is no half hidden person"
    )
)

val fakeProducts: List<Product> = listOf(
    Product(
        "1",
        "shoes",
        "you can fly with this shoes",
        "10000",
        "",5f,
        shoesReviews
    ),
    Product(
        "2",
        "shirt",
        "you can be hidden with this shirt you don`t want atleast try once",
        "20000",
        "",
        5f,
        shirtReviews
    )
)

val fakeThrowable = Throwable("sorry ")


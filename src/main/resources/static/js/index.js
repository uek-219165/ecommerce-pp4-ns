const getProducts = () => {
    return fetch('/api/products')
        .then(r => r.json());
}

const createProductHtmlElement = (product) => {
    const template = `
        <li class="product">
            <img class="product__image" src="${product.imageUrl}" alt="..."/>
            <h4 class="product__heading">${product.name}</h4>
            <span class="product__price">${product.price}</span>
            <button class="btn product__btn" data-product-id="${product.id}" >Add to cart</button>
        </li>
    `;

    return createHtmlElementFromString(template);
}

const createHtmlElementFromString = (htmlTemplate) => {
    const parentEl = document.createElement('div');
    parentEl.innerHTML = htmlTemplate.trim();

    return parentEl.firstChild;
}

(() => {
    const productListEl = document.querySelector('.products__list');

    getProducts()
        .then(products => {
            products
                .map(product => createProductHtmlElement(product))
                .forEach(productHtml => {
                    productListEl.appendChild(productHtml);
                });
        })
        .catch(e => console.log(e));
})();


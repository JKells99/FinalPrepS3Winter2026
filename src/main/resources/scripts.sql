-- Table: public.users
CREATE TABLE IF NOT EXISTS public.users
(
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Table: public.products
CREATE TABLE IF NOT EXISTS public.products
(
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_description TEXT,
    product_price DECIMAL(10, 2) NOT NULL,
    product_stock INTEGER NOT NULL
);

-- Table: public.instruments
CREATE TABLE IF NOT EXISTS public.instruments
(
    product_id INTEGER PRIMARY KEY REFERENCES public.products(product_id) ON DELETE CASCADE,
    instrument_type VARCHAR(100),
    instrument_brand VARCHAR(100),
    number_of_strings INTEGER,
    instrument_color VARCHAR(50)
);

-- Table: public.accessories
CREATE TABLE IF NOT EXISTS public.accessories
(
    product_id INTEGER PRIMARY KEY REFERENCES public.products(product_id) ON DELETE CASCADE,
    accessory_type VARCHAR(100),
    accessory_brand VARCHAR(100)
);

-- Table: public.invoices
CREATE TABLE IF NOT EXISTS public.invoices
(
    invoice_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES public.users(user_id),
    employee_id INTEGER REFERENCES public.users(user_id),
    total_amount DECIMAL(10, 2) NOT NULL,
    invoice_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: public.invoice_items
CREATE TABLE IF NOT EXISTS public.invoice_items
(
    invoice_id INTEGER REFERENCES public.invoices(invoice_id) ON DELETE CASCADE,
    product_id INTEGER REFERENCES public.products(product_id),
    PRIMARY KEY (invoice_id, product_id)
);

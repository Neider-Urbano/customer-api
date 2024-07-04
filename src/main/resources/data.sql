INSERT INTO customer_db.customer (name, email, phone_number)
SELECT * FROM (
    SELECT 'Neider Urbano' AS name, 'neider@example.com' AS email, '3204524545' AS phone_number
    UNION ALL
    SELECT 'Julian Bastilla', 'julian@example.com', '3202343800'
) AS new_customers
WHERE NOT EXISTS (
    SELECT 1 FROM customer WHERE customer.email = new_customers.email
);

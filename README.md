### **INCREASED SCOPE**

**Database:** Mysql

**Tables:**

1.Orders (orders)

    id bigint NOT NULL PRIMARY,
    created_ts timestamp NOT NULL,
    order_id varchar NOT NULL UNIQUE,
    user_id int NOT NULL,
    status varchar NOT NULL,
    schedule_ts timestamp NOT NULL,
    expected_delivery_ts timestamp NOT NULL,
    is_recurring boolean
    
1.OrderItems (order_items)

    id bigint NOT NULL PRIMARY,
    order_item_id varchar NOT NULL,
    order_id bigint FOREIGN KEY (ORDER TABLE id),
    status varchar NOT NULL,
    type varchar NOT NULL,
    text varchar,
    recurring_order_id bigint FOREIGN KEY (recurring_orders id)

3.Recurring Orders (recurring_orders)

    id bigint NOT NULL PRIMARY,
    created_ts timestamp NOT NULL,
    recurring_order_id varchar NOT NULL,
    user_id int NOT NULL,
    status varchar NOT NULL,
    last_recurred_ts timestamp,
    recurring_type varchar NOT NULL,
    recurring unit int NOT NULL;

**CRUD OPERATIONS**

1.Create Order

    Single Entry will be created in order table
    if recurring order create a entry in recurring order table also
    create entries in order item table for subsequent items in the order for above cases

2.Fetch Orders
    
    1st api will fetch the list of orders
    2nd api will fetch the items list in order

3.Delete Orders

    order can be cancelled as whole
    status will be changed to cancelled

4.Update Orders
    
    order_items entries will be updated or created
    based on the changes, subsequent changes will be applied on the order expecected_delivery_ts


**SCHEDULER**
    
    A scheduler will be run which check in the recurring order table if any order is there which is to be schedule and its corresponding entry should be created in the order table
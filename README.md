# Order_cart_assignment
Assignment

## PROBLEM STATEMENT :

You are running an e-commerce business like Grofers. You have received n orders for the day. You have to deliver these n orders to the customers in 4 delivery slots ( 6-9, 9-13,16-19,19-23 hours). There are delivery partners who deliver these orders. Each delivery partner has their own vehicle and each vehicle has a carrying capacity i.e total weight of the orders which is dependent on the vehicle type (bike, scooter, or truck).

Bike capacity - 30 kg per trip 

Scooter capacity  - 50 kg per trip

Truck capacity - 100 kg per trip 


Each slot can take orders with an overall max weight of 100 kg. There are no trucks available in the morning slot (6-9 hours) and there are no scooters and bikes available in the evening slot (19-23 hours). All vehicle types are available for the other two slots (9-13,16-19 hours). You can’t order more than 1 truck, 3 bikes, 2 scooters a day. Also, the vehicles’ capacity should be used optimally.

## MINI DOCUMENTATION 

<mark>NOTE</mark>: Given slots are represented with integers. So the 4 given slots are mapped with the following numbers
 *  1 ==> [6,9] Slot
 *  2 ==> [9,13] Slot
 *  3 ==> [16,19] Slot
 *  4 ==> [19, 23] Slot
 
 
The api exposes one end point **GetListOfAssignedVehicles**, which returns a list of Vehicles which will deliver the order given in as input.
  to access the endpoing, make a <mark>POST REQUEST</mark> at the following URL if running locally.
  <mark>localhost:8081/cart/3</mark>. where 3 is the slot number and "/cart" triggers the api. 
  
  The body to be sent should be of the following format:
  ```python
{    
	"submittedOrderRequestList": [  
		{  
			"orderId" : "1",  
			"orderWeight": "30"  
		},  
		{  
			"orderId" : "2",  
			"orderWeight": "10"  
		},  
		{  
			"orderId" : "3",  
			"orderWeight": "15"  
		},  
		{  
			"orderId" : "4",  
			"orderWeight": "5"  
		}  
	]    
}   
```

where <mark>submittedOrderRequest</mark> holds the list of order details.

And, the expected output is
```python
{
    "vehicleAssignedList": [
        {
            "vehicleType": "bike",
            "deliveryPartnerId": 1,
            "listOrderIdsAssigned": [
                1
            ]
        },
        {
            "vehicleType": "bike",
            "deliveryPartnerId": 2,
            "listOrderIdsAssigned": [
                2,
                3,
                4
            ]
        }
    ]
}
```

where <mark>VehicleAssignedList</mark> holds the list of vehicles which are assigned with the OrderId. 

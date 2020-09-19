# Order_cart_assignment
Assignment

## PROBLEM STATEMENT :

You are running an e-commerce business like Grofers. You have received n orders for the day. You have to deliver these n orders to the customers in 4 delivery slots ( 6-9, 9-13,16-19,19-23 hours). There are delivery partners who deliver these orders. Each delivery partner has their own vehicle and each vehicle has a carrying capacity i.e total weight of the orders which is dependent on the vehicle type (bike, scooter, or truck).

Bike capacity - 30 kg per trip 

Scooter capacity  - 50 kg per trip

Truck capacity - 100 kg per trip 


Each slot can take orders with an overall max weight of 100 kg. There are no trucks available in the morning slot (6-9 hours) and there are no scooters and bikes available in the evening slot (19-23 hours). All vehicle types are available for the other two slots (9-13,16-19 hours). You can’t order more than 1 truck, 3 bikes, 2 scooters a day. Also, the vehicles’ capacity should be used optimally.


## MINI DOCUMENTATION 

### TECH STACK USED
* Programming Language: Java
* Framework: Springboot
* BuildTool: Maven
* DataBase: MongoDB

#### NOTE: 
1) Given slots are represented with integers. So the 4 given slots are mapped with the following numbers
 *  1 ==> [6,9] Slot
 *  2 ==> [9,13] Slot
 *  3 ==> [16,19] Slot
 *  4 ==> [19, 23] Slot

2) The variables in this program were named using Java Naming Conventions. (camel case) 

### API ENDPOINT
 
**POST:** The api exposes one end point **GetListOfAssignedVehicles**, which returns a list of Vehicles which will deliver the order given in as input.
  to access the endpoint, make a **POST REQUEST** at the following URL if running locally.
  **localhost:8081/cart/3**. where **3** is the slot number and **/cart** triggers the api. 
  
  The body to be sent with the request should be of the following format:
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

where **submittedOrderRequest** holds the list of order details.

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

where **VehicleAssignedList** holds the list of vehicles which are assigned with the OrderId. 

### DATABASE STRUCTURE

There are 3 repositories created for this problem
* **Vehicle Repository**
  * Vehicle Repository stores VehicleEntity object/Document, and it has the following properties.
    * vehicleType (String)
    * maxWeightAllowd (Integer)
    * ListOfAvailableSlots (List<Integer>)
	
  * This Repository is populated when the Server starts.
  
* **Order Repository**
  * Order Repository stores OrderEntity object/document, and it has the followint properties.
    * slotKey (Integer)
    * orderId  (Integer)
    * orderWeight (Integer)

* **Slot Repository**

### APPROACH

To approach this problem I have used **0/1 KnapSack** Algorithm.

**ALGORITHM**
1) Get List of vehicles from the data baseb which are availabel at the given slot.
2) Get List of Orders which needs to be assigned to the vehicles
3) Iterate over each vehicle and and check if it can deliver any order, If yes, we update the HashSet which is used to keep track of **Orders Assigned**
4) Repeat **Step 3** untill all orders are assigned
5) Return list of vehicles which are assigned.

* Time Complexity: **O(V * N)**: as We Check Each vehicle with every order to check if it can accomodate it
* Space Complexity: **O(N)**: as we store the order id in HashSet

Where **V** is the number of available vehicles. I
Where **N** is the size or number of given orders

#### TRADEOFF
There is one trade off using **0/1 Knapsack** approach:
* since this is a 0/1 knapsace, if a vehicle with Enough capacity is Not present, then that order can go un attended
For Exampls: for Solt 1 (6, 9), if we get an Order for 100kg, and since we don't have a truck with us in the morning slot, this order can go unattended.
* This bug will be fixed in the next iteration of the code.




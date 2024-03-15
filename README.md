# User Requirements:

## Parking Violation Reporting:

1. Traffic police should be able to report instances of wrong parking.
2. They can capture and upload pictures of the parked car along with its license plate.
3. The system should allow for easy submission of this information to a central database.
   
## Violation Notification:

1. Upon submission of a parking violation report, the car owner associated with the license plate should be automatically fined.
2. An auto-generated SMS should be sent to the owner informing them about the violation and fine.

## Violation History Access:

1. Users should be able to access the violation history of a particular vehicle with a single click.
2. This feature is useful for both traffic police and car owners to track past violations.

## Challan Payment Options:

1. Car owners should have the option to pay the challan via e-payment.
2. Alternatively, the fine amount can be added to the annual tax of the car.

## System Requirements:

## Parking Violation Reporting Module:

1. The system should provide a user interface for traffic police to report parking violations.
2. It should allow for uploading images of parked cars and entering license plate information.
3. The information should be stored securely in a central database.

## Violation Notification System:

1. Upon submission of a violation report, the system should automatically identify the owner associated with the license plate.
2. An SMS service should be integrated to send automated notifications to the owner's registered phone number.

## Violation History Management:

1. The system should maintain a database of all reported violations linked to their respective vehicle license plates.
2. It should offer an interface for users to retrieve violation history by providing the license plate number.

## Challan Payment Module:

1. Integration with e-payment gateways should be implemented to facilitate online payment of fines.
2. Additionally, a mechanism should be in place to add unpaid fines to the annual tax records of vehicles.

## Security and Privacy:

1. The system should enforce proper authentication and authorization mechanisms to ensure only authorized users can access and modify data.
2. Personal information, such as license plate numbers and owner details, should be securely stored and handled according to privacy regulations.

## Scalability and Performance:

1. The system should be capable of handling a large volume of parking violation reports and user queries efficiently.
2. It should be designed with scalability in mind to accommodate future growth and increasing demand.

## Reliability and Availability:

1. Measures should be taken to ensure high availability of the system to minimize downtime.
2. Data redundancy and backup mechanisms should be in place to prevent data loss in case of system failures.

##########################################################################################################################################################################################################################################################################################################################################################################################################################################################

# Functional Requirements:
# Parking Violation Reporting Module:

F1: The system shall provide a user interface for traffic police to report parking violations.
F2: Traffic police shall be able to capture and upload pictures of parked cars.
F3: Traffic police shall be able to enter license plate information along with the pictures.
F4: The system shall securely store the submitted information in a central database.

## Violation Notification System:

F5: Upon submission of a violation report, the system shall automatically identify the car owner associated with the license plate.
F6: The system shall integrate with an SMS service to send automated notifications to the owner's registered phone number.
Violation History Management:

F7: The system shall maintain a database of all reported violations linked to their respective vehicle license plates.
F8: Users shall be able to retrieve violation history by providing the license plate number.

## Challan Payment Module:

F9: The system shall integrate with e-payment gateways to facilitate online payment of fines.
F10: The system shall provide a mechanism to add unpaid fines to the annual tax records of vehicles.
# Non-Functional Requirements:
## Security and Privacy:

N1: The system shall enforce proper authentication and authorization mechanisms to ensure only authorized users can access and modify data.
N2: Personal information, such as license plate numbers and owner details, shall be securely stored and handled according to privacy regulations.

## Scalability and Performance:

N3: The system shall be capable of handling a large volume of parking violation reports and user queries efficiently.
N4: The system shall be designed with scalability in mind to accommodate future growth and increasing demand.

## Reliability and Availability:

N5: Measures shall be taken to ensure high availability of the system to minimize downtime.
N6: Data redundancy and backup mechanisms shall be in place to prevent data loss in case of system failures.

# FURPS Validation:

Functionality (F): The functional requirements ensure that the system provides all necessary features for reporting violations, notifying owners, managing violation history, and processing payments.
Usability (U): While not explicitly mentioned, usability considerations could include an intuitive user interface for police officers and car owners to navigate through the system easily.
Reliability (R): The system's reliability is ensured through features like secure data storage, backup mechanisms, and high availability to prevent downtime.
Performance (P): Performance requirements ensure that the system can handle a large volume of data and user requests efficiently, supporting scalability for future growth.
Security (S): Security requirements ensure that the system enforces proper authentication and authorization mechanisms and handles personal information securely to comply with privacy regulations.

=========================================================================================================================================================================================================================================================================================================================================================================================================================================================
# Description:
## Traffic Police Officer:

1. External entity representing the traffic police officers who interact with the system.
2. They report parking violations by capturing pictures and entering license plate information.
3. Data Flow: The traffic police officer submits violation reports to the system.
## Car Owner:

1. External entity representing the owners of the parked cars.
2. They receive automated SMS notifications about parking violations and fines.
3. Data Flow: Car owners receive SMS notifications from the system.
## SMS Service Provider:

1. External service provider responsible for sending SMS notifications.
2. Integrated with the system to deliver automated notifications to car owners.
3. Data Flow: The system sends notification requests to the SMS service provider.
## Central Database:

1. Represents the central database where all violation reports and related data are stored.
2. Data Flow: Violation reports submitted by traffic police officers are securely stored in the central database.
## E-Payment Gateway:

1. External service provider facilitating online payment of fines.
2. Integrated with the system to process fine payments from car owners.
3. Data Flow: Car owners make e-payments through the system, which interacts with the e-payment gateway.
## Annual Tax Records:

1. Represents the records of annual taxes associated with vehicles.
2. Data Flow: Unpaid fines can be added to the annual tax records of vehicles through the system.
# Direction of Flow:
1. The flow of data originates from external entities (traffic police officers, car owners) and flows into the system.
2. The system processes this data, interacts with external services (SMS service provider, e-payment gateway), and stores relevant information in the central database.
3. Responses and notifications are then sent back to the external entities, completing the flow of data.

========================================================================================================================================================================================================================================================================================================================================================================================================================================================
# Entities and CRUD Operations:
## Parking Violation:

Create: Traffic police report parking violations.
Read: Users view violation details.
Update: Not applicable.
Delete: Not applicable.
## Violation History:

Create: Automatically created upon reporting a violation.
Read: Users access violation history.
Update: Not applicable.
Delete: Not applicable.
## Challan Payment:

Create: Car owners make e-payments for fines.
Read: Users view payment details.
Update: Not applicable.
Delete: Not applicable.

========================================================================================================================================================================================================================

# Sequence Diagram for Reporting Parking Violations:

## Description:
1. Traffic Police initiates violation report: Traffic police officer interacts with the system to report a parking violation.
2. Capture Picture and Enter Details: The system prompts the traffic police officer to capture a picture of the parked car and enter license plate information.
3. Submit Violation Report: After entering the details, the traffic police officer submits the violation report to the system.
4. Store in Database: The system securely stores the submitted violation report in the central database.
## Sequence Diagram for Making Payments for Fines:

## Description:
1. Car Owner initiates payment: Car owner interacts with the system to make a payment for a fine.
2. Select Payment Method: The system presents payment options to the car owner, including e-payment and adding the fine to the annual tax records.
3. Enter Payment Details: Car owner enters payment details such as card information for e-payment.
4. Process Payment: The system processes the payment using the selected payment method.
5. Update Payment Status: Upon successful payment processing, the system updates the payment status for the fine in the database.


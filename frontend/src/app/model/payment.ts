export interface payment{
"transactionId":number,
"paymentAmount":number,
"transactionDate":string,
"paymentMethod":string,
"isEarly":boolean,
"isOnline":boolean,
"mappedInvoice":number,
"mappedCustomer":number
}
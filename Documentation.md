# 📘 Loan Calculator API Documentation

## 🚀 POST `/api/loans/calculate`

Calculates the loan payment schedule based on the provided loan parameters.

---

## 📥 Request Body (JSON)

```json
{
  "amount": 1000.0,
  "annualInterestRate": 5.0,
  "termInMonths": 12,
  "startDate": "2025-05-05"
}

```
## 🔍 Request Fields

| Field               | Type    | Required | Description                                              | Constraints             |
|---------------------|---------|----------|----------------------------------------------------------|-------------------------|
| `amount`            | decimal | ✅        | Total loan amount                                        | 0.01 – 1,000,000        |
| `annualInterestRate`| decimal | ✅        | Annual interest rate in percent (e.g., 5.5 = 5.5%)       | 0 – 100                 |
| `termInMonths`      | integer | ✅        | Loan term duration in months                             | 1 – 1200                |
| `startDate`         | string  | ✅        | Loan start date in `YYYY-MM-DD` format                   | Current or future date  |


## 📤 Response Body (JSON)

```json
{
  "monthlyPayment": 85.61,
  "totalPayment": 1027.32,
  "totalInterest": 27.32,
  "schedule": [
    {
      "month": 1,
      "date": "2025-06-05",
      "payment": 85.61,
      "principal": 85.61,
      "interest": 0,
      "remainingBalance": 914.39
    },
    {
      "month": 2,
      "date": "2025-07-05",
      "payment": 85.61,
      "principal": 85.04,
      "interest": 0.57,
      "remainingBalance": 829.35
    },
    {
      "month": 3,
      "date": "2025-08-05",
      "payment": 85.61,
      "principal": 85.04,
      "interest": 0.48,
      "remainingBalance": 744.31
    }
  ]
}
```
### 🔍 Response Fields

| Field                | Type     | Description                                                                 |
|----------------------|----------|-----------------------------------------------------------------------------|
| `monthlyPayment`     | decimal  | Monthly payment amount, including both principal and interest.               |
| `totalPayment`       | decimal  | Total amount paid over the course of the loan, including interest.           |
| `totalInterest`      | decimal  | Total interest paid over the course of the loan.                            |
| `schedule`           | array    | List of loan payments by month. Each entry contains details of the payment. |
| `month`              | integer  | The month number (1-based).                                                  |
| `date`               | string   | The payment date in `YYYY-MM-DD` format.                                    |
| `payment`            | decimal  | The total payment for that month (principal + interest).                    |
| `principal`          | decimal  | The portion of the payment that goes toward paying down the principal.       |
| `interest`           | decimal  | The portion of the payment that goes toward paying interest.                |
| `remainingBalance`   | decimal  | The remaining balance of the loan after the payment is applied.              |

### 🔑 Error Handling

In case of errors, the API will return an error response with the following format:

#### Error Response Format

```json
{
  "error": {
    "code": "ERROR_CODE",
    "message": "Error description",
    "details": "Additional information (optional)"
  }
}
```
### Error Response Fields

| Field      | Type   | Description                                                      |
|------------|--------|------------------------------------------------------------------|
| `error`    | object | Contains error details.                                          |
| `code`     | string | A unique error code that identifies the type of error.           |
| `message`  | string | A human-readable description of the error.                        |
| `details`  | string | Additional information about the error (optional). 


#### Error Response Fields

| Field      | Type   | Description                                                      |
|------------|--------|------------------------------------------------------------------|
| `error`    | object | Contains error details.                                          |
| `code`     | string | A unique error code that identifies the type of error.           |
| `message`  | string | A human-readable description of the error.                        |
| `details`  | string | Additional information about the error (optional).                |

#### Example of an Error Response:

```json
{
  "error": {
    "code": "INVALID_INPUT",
    "message": "The provided loan amount exceeds the allowed limit.",
    "details": "Loan amount must be between $1,000 and $500,000."
  }
```

## Swagger UI Integration

The API provides a Swagger interface for easy testing and exploration of the endpoints. Swagger is an interactive tool that allows you to see all available API endpoints and try them out directly from your browser.

### How to Access Swagger UI

To access the Swagger UI for this API, follow these steps:

1. Start the application.
2. Open a web browser and navigate to the following URL:
http://localhost:8080/swagger-ui/index.html


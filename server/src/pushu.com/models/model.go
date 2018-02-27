package modes

type AppInfo struct {
    Version string `json:"version"`
}

type DeviceInfo struct {
    Id string `json:"id"`
    OS string `json:"os"`
    Version string `json:"version"`
    AppInfo AppInfo
}

type User struct{
    Id int64 `json:"id"`
    Mobile string `json:"mobile"`
    FirstName string `json:"firstname"`
    LastName string `json:"lastname"`
    IdCardNo string `json:"idcardno"`
}

type UserAuth struct {
    Mobile string `json:"mobile"`
    VCode string `json:"vcode"`
    DeviceInfo DeviceInfo
}

type UserToken struct {
    Id int64  `json:"id"`
    ExpireDate string `json:"expiredate"`
}

type UserCard struct {
    Id int64 `json:"id"`
    Bank string `json:"bank"`
    Number string `json:"number"`
    CartType string `json:"cardtype"`
}

type UserAccount struct {
    Id int64 `json:"id"`
    UserId int64 `json:"userid"`
    Balance float64 `json:"balance"`
    Cards UserCard[]
}

type Transaction struct {
    Id int64 `json:"id"`
    RecieverId int64 `json:"recieverid"`
    Amount float64 `json:"amount"`
    TransType string `json:"transtype"`
    PayType string `json:"paytype"`
    PayBalance float64 `json:"paybalance"`
    PayCard UserCard `json:"paycard"`
    PayCardAmount float64 `json:"paycardamount"`
}


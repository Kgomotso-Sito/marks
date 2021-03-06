export class User {
    constructor(
        public id : string,
        public userNumber: string,
        public email: string,
        public role : string,
        public title : string,
        public fullName : string,
        public lastName : string,
        public schoolGrade : string,
        public schoolClass : string,
        public gender : string,
        public race : string,
        public birthDate : Date,
        public nationality : string,
        public idNumber : string,
        public passportNumber : null,
        public emailAddress : string,
        public phoneNumber : string,
        public houseNo : string,
        public city : string,
        public province : string,
        public active : boolean,
        public streetNo : string) {};
}
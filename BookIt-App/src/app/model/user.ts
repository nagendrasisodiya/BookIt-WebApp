import {Book} from './book';

export interface User {
  id?:Number;
  username: string;
  email: string;
  password: string;
  role?: string;
  books:Book[];
}

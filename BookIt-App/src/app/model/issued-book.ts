export interface IssuedBook {
  bookId: number;
  title: string;
  author: string;
  category: string;
  issueDate: string;
  returnDate: string;
  userId?: number;
  userName?: string;
}

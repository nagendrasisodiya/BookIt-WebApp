import { Component, OnInit } from '@angular/core';
import { NgForOf, NgIf } from '@angular/common';
import { BookRequest } from '../../model/book-request';
import { AdminService } from '../../Service/admin.service';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-remote-book-requests',
  templateUrl: './remote-book-requests.component.html',
  imports: [
    NgForOf,
    NgIf
  ],
  styleUrls: ['./remote-book-requests.component.css']
})
export class RemoteBookRequestsComponent implements OnInit {
  bookRequests: BookRequest[] = [];
  processing: boolean = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadRemoteBookRequests();
  }

  loadRemoteBookRequests(): void {
    this.adminService.getRemoteBookRequests$().subscribe({
      next: (requests) => {
        this.bookRequests = requests;
      },
      error: (error) => {
        console.error('Error loading remote book requests:', error);
      }
    });
  }

  assignBook(request: BookRequest): void {
    // Prevent multiple clicks
    if (this.processing) return;
    this.processing = true;

    if (request.userId === undefined || request.bookId === undefined) {
      console.error('Cannot assign book: userId or bookId is undefined');
      alert('Book assignment failed: required information is missing.');
      this.processing = false;
      return;
    }

    if (request.requestId === undefined) {
      console.error('Cannot complete request: requestId is undefined');
      alert('Request completion failed: request ID is missing.');
      this.processing = false;
      return;
    }

    this.adminService.assignBook$(request.userId, request.bookId)
      .pipe(
        finalize(() => this.processing = false)
      )
      .subscribe({
        next: () => {
          this.adminService.completeRemoteBookRequest$(request.requestId).subscribe({
            next: () => {
              this.bookRequests = this.bookRequests.filter(
                req => req.requestId !== request.requestId
              );
            },
            error: (error) => {
              console.error('Error completing remote book request:', error);
              alert('Failed to mark request as completed. Please try again.');
            }
          });
        },
        error: (error) => {
          console.error('Error assigning book:', error);
          alert('Failed to assign book. Please try again.');
        }
      });
  }
}

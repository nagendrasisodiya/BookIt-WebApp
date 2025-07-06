import {HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest} from '@angular/common/http';

export const jwtInterceptor: HttpInterceptorFn = (req,
                                                  next) => {
  const token = localStorage.getItem('token');
  if (token) {
    console.log('Token being sent:', token);
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  return next(req);
};

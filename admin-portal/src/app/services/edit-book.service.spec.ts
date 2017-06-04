import { TestBed, inject } from '@angular/core/testing';

import { EditBookService } from './edit-book.service';

describe('EditBookService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EditBookService]
    });
  });

  it('should ...', inject([EditBookService], (service: EditBookService) => {
    expect(service).toBeTruthy();
  }));
});

import { TestBed } from '@angular/core/testing';

import { BackendAPIService } from './backend-api.service';

describe('BackendAPIService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BackendAPIService = TestBed.get(BackendAPIService);
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { WebRestService } from './web-rest.service';

describe('WebRestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WebRestService = TestBed.get(WebRestService);
    expect(service).toBeTruthy();
  });
});

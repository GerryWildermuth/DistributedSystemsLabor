import { TestBed } from '@angular/core/testing';

import { SynchronizerService } from './synchronizer.service';

describe('SynchronizerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SynchronizerService = TestBed.get(SynchronizerService);
    expect(service).toBeTruthy();
  });
});

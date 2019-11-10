import { RequestObj } from './requestObj';

export class Package {
    id: number;
    approvals;
    department;
    pdfFile: File;
    requests: RequestObj[];
    supportingDocuments: File[];
}
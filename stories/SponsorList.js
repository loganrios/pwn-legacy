import React from "react";
import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";

const columns = [
  { field: 'username',
    headerName: 'Sponsors',
    width: 150,
  },
  { field: 'donation',
    headerName: 'Amount',
    width: 150,
    type: "number",
  },
];

export default function SponsorList ({sponsors}) {
  return (
    <Box sx={{ height: 400, width: '100%' }}>
    <DataGrid
      rows={sponsors}
      columns={columns}
      pageSize={5}
      rowsPerPageOptions={[10]}
    />
    </Box>
  );
};


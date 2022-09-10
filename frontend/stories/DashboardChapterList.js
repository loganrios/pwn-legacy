import React from "react";
import { Box, Pagination, Button } from "@mui/material";

const columns = [
  { field: "title", headerName: "Title", width: 150 },
  { field: "date", headerName: "Date", width: 150 },
  { field: "words", headerName: "Words", width: 150, type: "number" },
  { field: "views", headerName: "Views", width: 150, type: "number" },
  { field: "comment", headerName: "Comment", width: 150 },
  {
    field: "controls",
    sortable: false,
    type: "actions",
    headerName: "Controls",
    width: 150,
  },
];

const DashboardChapterList = ({ chapters }) => {
  return <Box sx={{ height: 400, width: "100%" }}></Box>;
};

export default DashboardChapterList;

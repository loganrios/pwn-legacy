import React from "react";
import {
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
} from "@mui/material";

const columns = [
  { id: "title", label: "Title", width: 150 },
  { id: "date", label: "Date", width: 150 },
  { id: "words", label: "Words", width: 150, type: "number", align: "right" },
  { id: "views", label: "Views", width: 150, type: "number", align: "right" },
  { id: "comment", label: "Comment", width: 150, align: "right" },
  {
    id: "controls",
    sortable: false,
    type: "actions",
    label: "Controls",
    width: 150,
    align: "center",
  },
];

const DashboardChapterList = ({ chapters }) => {
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  return (
    <Paper sx={{ width: "100%", overflow: "hidden" }}>
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            <TableRow>
              {columns.map((column) => (
                <TableCell
                  key={column.id}
                  align={column.align}
                  style={{ minWidth: column.minWidth }}
                >
                  {column.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {chapters
              ?.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              ?.map((item) => (
                <TableRow hover key={item.id}>
                  <TableCell>{item.title}</TableCell>
                  <TableCell>{item.date}</TableCell>
                  <TableCell align="right">{item.words}</TableCell>
                  <TableCell align="right">{item.views}</TableCell>
                  <TableCell align="right">{item.comment}</TableCell>
                  <TableCell align="center">
                    <Button variant="contained" onClick={item.onEdit}>
                      Edit
                    </Button>
                    <Button
                      variant="contained"
                      onClick={item.onDelete}
                      color="error"
                    >
                      Delete
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[5, 10, 25, 100]}
        component="div"
        count={chapters.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
};

export default DashboardChapterList;

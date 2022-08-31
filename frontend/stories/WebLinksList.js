import React from "react";
import {
  Box,
  Link,
  Button,
  IconButton,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";

function WebLinksDialog({ onEditText, onSubmit, textFields }) {

  const [open, setOpen] = React.useState(false);

  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <>
      <IconButton aria-label="edit" onClick={handleOpen}>
        <EditIcon />
      </IconButton>
      <Dialog open={open} onClose={handleClose} scroll="paper">
        <DialogTitle id="weblinkslist-dialog">Links</DialogTitle>
        <DialogContent sx={{ display: "flex", flexDirection: "column" }}>
          {textFields.map((list) => (
            <TextField
              label={list.label}
              defaultValue={list.url}
              onChange={onEditText}
            />
          ))}
        </DialogContent>
        <DialogActions>
          <Button
            variant="contained"
            onClick={() => {
              onSubmit(); handleClose();
            }}
          >
            Save Changes
          </Button>
          <Button variant="outlined" onClick={handleClose}>
            Cancel
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
}

const WebLinksList = ({ links, fields, readingList, onEdit, onSubmit, }) => {
  return (
    <Box sx={{ display: "flex", flex: 1, flexDirection: "row", }}>
      <Box sx={{ display: "flex", flex: 1, flexDirection: "column", }}>
        <Link underline="hover"
          color="inherit"
          href={readingList}>
          Reading List
        </Link>
        {links.map((list) => (
          <Box sx={{ display: "flex" }}>
            <Link underline="hover"
              color="inherit"
              href={list.url}>
              {list.label}
            </Link>
          </Box>
        ))}
      </Box>
      <Box>
        <WebLinksDialog
          onEditText={onEdit}
          onSubmit={onSubmit}
          textFields={fields}
        />
      </Box>
    </Box>
  );
};

export default WebLinksList;

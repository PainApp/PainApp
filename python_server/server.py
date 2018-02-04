from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import json

class S(BaseHTTPRequestHandler):
    body_regions_dict = {
        "body_regions" : [
        {
            "id" : 0,
            "name" : "Front Hip"
        }, 
        {
            "id" : 1,
            "name" : "Front Head"
        },
        {
            "id" : 2,
            "name" : "Front Feet"
        }]
    }

    specific_regions = {
        0 : [
            {
                "id" : 0,
                "name" : "Deep Hip"
            },
            {
                "id" : 1,
                "name" : "Anterolateral"
            },
            {
                "id" : 2,
                "name" : "Posterior"
            },
            {
                "id" : 3,
                "name" : "Anterior"
            },
            {
                "id" : 4,
                "name" : "Medial"
            },
            {
                "id" : 5,
                "name" : "Lateral"
            }]
    }

    causes = {
        0 : {
            2 : [
                {
                    "id" : 0,
                    "name" : "Gluteus Maximus Bursitis",
                    "classification" : "default"
                },
                {
                    "id" : 1,
                    "name" : "Coccydynia",
                    "classification" : "default"
                },
                {
                    "id" : 2,
                    "name" : "Ischial Tuberosity Avulsion Fracture",
                    "classification" : "children"
                }],
            4 : [
                {
                    "id" : 0,
                    "name" : "Adductor Tendinitis",
                    "classification" : "default"
                },
                {
                    "id" : 1,
                    "name" : "Ramus Avulsion Fracture",
                    "classification" : "children"
                },
                {
                    "id" : 2,
                    "name" : "Testicular Torsion",
                    "classification" : "extreme"
                }]
        }
    }


    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-Type', 'application/json')
        self.end_headers()

    def do_GET(self):
        if self.path == "/PocketDoc/body_regions":
            self._set_headers()
            out = json.dumps(self.body_regions_dict)
            self.wfile.write(out)
        else:
            self.send_error(404)

    def do_HEAD(self):
        self._set_headers()

    def do_POST(self):
        if self.path == "/PocketDoc/body_regions":
            self.do_body_regions_post()
        elif self.path == "/PocketDoc/specific_regions":
            self.do_specific_regions_post()
        elif self.path == "/PocketDoc/causes":
            self.do_causes_post()
        else:
            self.send_error(404)

    def do_body_regions_post(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        post_data = json.loads(post_data)
        if "id" not in post_data:
            self.send_error(400)
            return
        body_region_id = post_data["id"]
        if body_region_id < 0 or body_region_id >= len(self.body_regions_dict["body_regions"]):
            self.send_error(404)
            return
        data = dict(self.body_regions_dict["body_regions"][body_region_id])
        lst = []
        if body_region_id in self.specific_regions:
            lst = self.specific_regions[body_region_id]
        data["specific_regions"] = lst
        self._set_headers()
        self.wfile.write(json.dumps(data))

    def do_specific_regions_post(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        post_data = json.loads(post_data)
        if ("id" not in post_data) or ("body_region_id" not in post_data):
            self.send_error(400)
            return
        body_region_id = post_data["body_region_id"]
        specific_region_id = post_data["id"]
        if (body_region_id not in self.specific_regions) or (specific_region_id < 0) or (specific_region_id >= len(self.specific_regions[body_region_id])):
            self.send_error(404)
            return
        data = dict(self.specific_regions[body_region_id][specific_region_id])
        lst = []
        if (body_region_id in self.causes) and (specific_region_id in self.causes[body_region_id]):
            lst = self.causes[body_region_id][specific_region_id]
        data["causes"] = lst
        self._set_headers()
        self.wfile.write(json.dumps(data))

    def do_causes_post(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        post_data = json.loads(post_data)
        if ("id" not in post_data) or ("body_region_id" not in post_data) or ("specific_region_id" not in post_data):
            self.send_error(400)
            return
        body_region_id = post_data["body_region_id"]
        specific_region_id = post_data["specific_region_id"]
        cause_id = post_data["id"]
        if (body_region_id not in self.causes) or (specific_region_id not in self.causes[body_region_id]) or (cause_id < 0) or (cause_id >= len(self.causes[body_region_id][specific_region_id])):
            self.send_error(404)
            return
        data = dict(self.causes[body_region_id][specific_region_id][cause_id])
        self._set_headers()
        self.wfile.write(json.dumps(data))

def run(server_class=HTTPServer, handler_class=S, port=8080):
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)
    print('Starting httpd...')
    httpd.serve_forever()

if __name__ == "__main__":
    from sys import argv

    if len(argv) == 2:
        run(port=int(argv[1]))
    else:
        run()
